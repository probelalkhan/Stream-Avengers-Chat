package com.example.avengers_chat.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.avengers_chat.R
import com.example.avengers_chat.base.BaseActivity
import com.example.avengers_chat.data.Avenger
import com.example.avengers_chat.databinding.ActivityAvengersChannelListBinding
import com.example.avengers_chat.extensions.getParcelableOrThrow
import io.getstream.chat.android.livedata.ChatDomain
import io.getstream.chat.android.ui.channel.list.viewmodel.ChannelListViewModel
import io.getstream.chat.android.ui.channel.list.viewmodel.bindView
import io.getstream.chat.android.ui.channel.list.viewmodel.factory.ChannelListViewModelFactory

class AvengersChannelListActivity : BaseActivity<ActivityAvengersChannelListBinding>(
    ActivityAvengersChannelListBinding::inflate
) {

    private lateinit var viewModel: AvengersChannelListViewModel
    private lateinit var avenger: Avenger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        avenger = intent.getParcelableOrThrow(KEY_AVENGER)
        viewModel = ViewModelProvider(this, factory).get(AvengersChannelListViewModel::class.java)
        viewModel.connectUser(avenger)

        val factory = ChannelListViewModelFactory()
        val channelListViewModel by viewModels<ChannelListViewModel> { factory }

        channelListViewModel.bindView(binding.channelListView, this)

        ChatDomain.instance().user.observe(this){ user ->
            if(user != null){
                binding.channelListHeaderView.setUser(user)
                binding.channelListHeaderView.showOnlineTitle()
                binding.channelListHeaderView.setOnlineTitle(getString(R.string.app_name))
            }
        }
    }

    companion object{
        private const val KEY_AVENGER = "key_avenger"
        fun startActivity(context: Context, avenger: Avenger){
            Intent(context, AvengersChannelListActivity::class.java).also {
                it.putExtra(KEY_AVENGER, avenger)
                context.startActivity(it)
            }
        }
    }
}