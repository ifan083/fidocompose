package com.example.fidoreregistration.questionnaire

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.fidoreregistration.questionnaire.data.*
import com.example.fidoreregistration.questionnaire.ui.QuestionScreenDescription
import com.example.fidoreregistration.wizard.Action
import com.example.fidoreregistration.wizard.Wizard

class QuestionnaireViewModel(
    entry: Wizard.Entry,
    private val navigateToFragment: (Wizard.FragmentDestination) -> Unit,
    private val navigateToActivity: (Wizard.ActivityDestination) -> Unit
) : ViewModel() {

    private val steps = mutableListOf<WizardStep>()

    init {
        val firstStep = when (entry) {
            ReRegistrationEntry.AppStart -> ReRegistration.AppStart
            ReRegistrationEntry.ActivatePermissions -> ReRegistration.ActivatePush
            ReRegistrationEntry.SetupTouchIdLogin -> ReRegistration.SetupTouchIdLogin
            else -> throw RuntimeException("Invalid entry")
        }
        steps.add(0, DataGenerator.reRegistrationStep(firstStep))
    }

    private var currentStep by mutableStateOf(0)

    val currentScreenDescription: QuestionScreenDescription
        get() = steps[currentStep].screenDescription()

    fun positiveAction() {
        navigate(steps[currentStep].positiveAction())
    }

    fun negativeAction() {
        navigate(steps[currentStep].negativeAction())
    }

    fun goBack() {
        if (currentStep > 0) {
            steps.removeAt(currentStep)
            currentStep--
        }
    }

    private fun navigate(action: Action) = when (action) {
        is Action.Continue -> {
            if (action.destination is Wizard.NoDestination) {
                navigateToActivity(Wizard.NoActivityDestination)
            }
            val nextStep = DataGenerator.reRegistrationStep(action.destination)
            steps.add(currentStep + 1, nextStep)
            currentStep++
        }
        is Action.Finish.ToFragment -> navigateToFragment(action.fragmentDestination)
        is Action.Finish.ToActivity -> navigateToActivity(action.activityDestination)
    }
}
