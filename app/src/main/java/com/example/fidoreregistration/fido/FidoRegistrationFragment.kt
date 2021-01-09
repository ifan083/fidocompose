package com.example.fidoreregistration.fido

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import com.example.fidoreregistration.ActivityNavigation
import com.example.fidoreregistration.BaseFragment
import com.example.fidoreregistration.FragmentNavigation
import com.example.fidoreregistration.fido.ui.BiometryDialog
import com.example.fidoreregistration.fido.ui.EUserSelection
import com.example.fidoreregistration.questionnaire.data.ReRegistrationFragment
import com.example.fidoreregistration.questionnaire.ui.EUser
import com.example.fidoreregistration.theme.FidoReregistrationTheme
import com.example.fidoreregistration.theme.bottomDrawerShape
import com.example.fidoreregistration.wizard.Wizard

class FidoRegistrationFragment : BaseFragment() {

    companion object {
        private const val EUSER = "euser"

        fun create(eUser: EUser): FidoRegistrationFragment {
            val bundle = Bundle().apply {
                putSerializable(EUSER, eUser)
            }
            return FidoRegistrationFragment().apply {
                arguments = bundle
            }
        }
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
        val eUser = arguments?.getSerializable(EUSER) as EUser

        return ComposeView(requireContext()).apply {
            setContent {
                FidoReregistrationTheme {
                    Surface(
                        color = MaterialTheme.colors.background,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        val state =
                            rememberBottomDrawerState(initialValue = BottomDrawerValue.Closed)
                        val closeBottomSheet = { state.close() }
                        val openBottomSheet = { state.open() }
                        BottomDrawerLayout(
                            drawerShape = bottomDrawerShape,
                            gesturesEnabled = false,
                            drawerState = state,
                            drawerContent = {
                                BiometryDialog(
                                    closeBottomSheet = closeBottomSheet,
                                    onSuccess = {
                                        fragmentNavigation.toFragment(
                                            ReRegistrationFragment.Activate3DSCardSelection
                                        )
                                    },
                                    onCancel = { /* do nothing on purpose */ })
                            },
                            bodyContent = {
                                EUserSelection(
                                    openBottomSheet = openBottomSheet,
                                    onBack = { fragmentNavigation.goBack() },
                                    onExit = { activityNavigation.toActivity(Wizard.NoActivityDestination) })
                            }
                        )

                    }
                }
            }
        }
    }
}

sealed class Outcome<T> {
    class Success<T>(val value: T) : Outcome<T>()
    class Error(val message: String) : Outcome<Nothing>()
}