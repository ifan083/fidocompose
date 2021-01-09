package com.example.fidoreregistration.complete

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import com.example.fidoreregistration.ActivityNavigation
import com.example.fidoreregistration.BaseFragment
import com.example.fidoreregistration.questionnaire.data.ReRegistrationActivity
import com.example.fidoreregistration.theme.FidoReregistrationTheme
import com.example.fidoreregistration.wizard.Wizard

class ReRegistrationCompleteFragment : BaseFragment() {

    companion object {
        private const val TITLE = "title"
        private const val SUBTITLE = "subtitle"
        fun create(
            title: String = "title",
            subtitle: String = "subtitle"
        ): ReRegistrationCompleteFragment {
            val bundle = Bundle().apply {
                putString(TITLE, title)
                putString(SUBTITLE, subtitle)
            }
            return ReRegistrationCompleteFragment().apply {
                arguments = bundle
            }
        }
    }

    private lateinit var title: String
    private lateinit var subtitle: String

    private lateinit var activityNavigation: ActivityNavigation

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityNavigation = context as ActivityNavigation
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        extractArguments()
        return ComposeView(requireContext()).apply {
            setContent {
                FidoReregistrationTheme {
                    Surface(
                        color = MaterialTheme.colors.background,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        CompleteScreen(
                            title = title,
                            subtitle = subtitle,
                            onCompleteClick = { activityNavigation.toActivity(ReRegistrationActivity.AssetOverview) },
                            onExit = { activityNavigation.toActivity(Wizard.NoActivityDestination) }
                        )
                    }
                }
            }
        }
    }

    private fun extractArguments() {
        title = arguments?.getString(TITLE) ?: ""
        subtitle = arguments?.getString(SUBTITLE) ?: ""
    }
}