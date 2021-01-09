package com.example.fidoreregistration.activate3ds

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.example.fidoreregistration.ActivityNavigation
import com.example.fidoreregistration.BaseFragment
import com.example.fidoreregistration.FragmentNavigation
import com.example.fidoreregistration.activate3ds.ui.CardActivationScreen
import com.example.fidoreregistration.common.Strings
import com.example.fidoreregistration.questionnaire.data.ReRegistrationActivity
import com.example.fidoreregistration.questionnaire.data.ReRegistrationFragment
import com.example.fidoreregistration.theme.FidoReregistrationTheme

class Activate3DSFragment : BaseFragment() {

    companion object {
        fun create(): Activate3DSFragment = Activate3DSFragment()
    }

    private lateinit var fragmentNavigation: FragmentNavigation
    private lateinit var activityNavigation: ActivityNavigation

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentNavigation = context as FragmentNavigation
        activityNavigation = context as ActivityNavigation
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val viewModel by viewModels<Activate3DSViewModel>()

        viewModel.activationResult.observe(viewLifecycleOwner) { activationEvent ->
            activationEvent.getContentIfNotHandled()?.let {
                if (it == "") {
                    return@let
                }

                Handler(Looper.getMainLooper()).postDelayed({
                    fragmentNavigation.toFragment(ReRegistrationFragment.Activate3DSCredentials)
                }, 1000L)
            }
        }

        return ComposeView(requireContext()).apply {
            setContent {
                FidoReregistrationTheme {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        CardActivationScreen(
                            cards = viewModel.selectableCards.observeAsState(),
                            onExitClick = { activityNavigation.toActivity(ReRegistrationActivity.WelcomeScreen) },
                            onBackClick = { fragmentNavigation.goBack() },
                            onInfoClick = {
                                Toast.makeText(
                                    activity,
                                    "Info Clicked",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            onNextClick = viewModel::activateCards,
                            onNotNowClick = {
                                fragmentNavigation.toFragment(
                                    ReRegistrationFragment.Complete(
                                        title = Strings.registrationSuccessTitle(),
                                        subtitle = Strings.registrationSuccessSubtitle()
                                    )
                                )
                            },
                            onCardSelected = viewModel::updateCardSelection
                        )
                    }
                }
            }
        }
    }
}