package com.example.fidoreregistration.activate3ds

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.example.fidoreregistration.ActivityNavigation
import com.example.fidoreregistration.BaseFragment
import com.example.fidoreregistration.FragmentNavigation
import com.example.fidoreregistration.activate3ds.ui.CredentialsScreen
import com.example.fidoreregistration.common.Strings
import com.example.fidoreregistration.questionnaire.data.ReRegistrationFragment
import com.example.fidoreregistration.theme.FidoReregistrationTheme
import com.example.fidoreregistration.wizard.Wizard


class Activate3DSCredentialsFragment : BaseFragment() {

    companion object {
        fun create() = Activate3DSCredentialsFragment()
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
    ): View = ComposeView(requireContext()).apply {

        val viewModel by viewModels<Activate3DSCredentialsViewModel>()

        viewModel.confirmResult.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                if (it == "") {
                    return@let
                }

                (activity as AppCompatActivity).hideKeyboard()
                Handler(Looper.getMainLooper()).postDelayed({
                    fragmentNavigation.toFragment(
                        ReRegistrationFragment.Complete(
                            title = Strings.registrationSuccessTitle(),
                            subtitle = Strings.registrationSuccessSubtitle()
                        )
                    )
                }, 1000L)
            }
        }

        setContent {
            FidoReregistrationTheme {
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    CredentialsScreen(
                        credentials = viewModel.credentials.observeAsState(),
                        onBackClick = { fragmentNavigation.goBack() },
                        onExitClick = { activityNavigation.toActivity(Wizard.NoActivityDestination) },
                        onConfirmClick = viewModel::confirm,
                        onPasswordEntered = viewModel::onPasswordEntered
                    )
                }
            }
        }
    }
}

fun AppCompatActivity.hideKeyboard() {
    val inputMethodManager = this.getSystemService(
        Activity.INPUT_METHOD_SERVICE
    ) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(
        this.currentFocus!!.windowToken, 0
    )
}