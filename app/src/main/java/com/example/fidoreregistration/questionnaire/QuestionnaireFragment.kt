package com.example.fidoreregistration.questionnaire

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.platform.ComposeView
import com.example.fidoreregistration.ActivityNavigation
import com.example.fidoreregistration.BaseFragment
import com.example.fidoreregistration.FragmentNavigation
import com.example.fidoreregistration.questionnaire.data.ReRegistrationEntry
import com.example.fidoreregistration.questionnaire.ui.QuestionScreen
import com.example.fidoreregistration.theme.FidoReregistrationTheme

class QuestionnaireFragment : BaseFragment() {

    companion object {
        private const val ENTRY = "entry"

        fun from(entry: ReRegistrationEntry = ReRegistrationEntry.AppStart): QuestionnaireFragment {
            val bundle = Bundle().apply {
                putSerializable(ENTRY, entry)
            }
            return QuestionnaireFragment().apply { arguments = bundle }
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
        val entry = arguments?.getSerializable(ENTRY) as ReRegistrationEntry
        val viewModel = QuestionnaireViewModel(
            entry = entry,
            navigateToFragment = { fragmentNavigation.toFragment(it) },
            navigateToActivity = { activityNavigation.toActivity(it) }
        )

        return ComposeView(requireContext()).apply {
            setContent {
                FidoReregistrationTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(color = MaterialTheme.colors.background) {
                        QuestionScreen(
                            screenDescription = viewModel.currentScreenDescription,
                            onPosBtnClick = viewModel::positiveAction,
                            onNegBtnClick = viewModel::negativeAction,
                            onBackClick = viewModel::goBack,
                            onExitClick = { fragmentNavigation.goBack() })

                    }
                }
            }
        }
    }
}

