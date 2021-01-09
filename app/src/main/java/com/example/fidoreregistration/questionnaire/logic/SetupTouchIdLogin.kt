package com.example.fidoreregistration.questionnaire.logic

import com.example.fidoreregistration.questionnaire.data.ReRegistration
import com.example.fidoreregistration.questionnaire.data.ReRegistrationActivity
import com.example.fidoreregistration.questionnaire.data.ReRegistrationFragment
import com.example.fidoreregistration.questionnaire.data.WizardStep
import com.example.fidoreregistration.questionnaire.ui.EUser
import com.example.fidoreregistration.questionnaire.ui.QuestionScreenDescription
import com.example.fidoreregistration.wizard.Action

class SetupTouchIdLogin : WizardStep {

    override fun destination() = ReRegistration.SetupTouchIdLogin

    override fun screenDescription() = QuestionScreenDescription(
        title = "Set up Touch ID login",
        subtitle = "Touch ID login is faster and allows access to all functions of the app.",
        posBtnText = "Activate Touch ID login",
        negBtnText = "Not now",
        imgUrl = QuestionScreenDescription.Img.PADLOCK.url,
        canGoBack = true,
        canExit = false
    )

    override fun positiveAction(): Action {
        if (!Util.isPushEnabled()) {
            return Action.Continue(ReRegistration.ActivatePush)
        }
        if (!Util.isFaceIdEnabled()) {
            return Action.Continue(ReRegistration.ActivateFaceId)
        }
        if (!Util.isUserLoggedIn()) {
            return Action.Finish.ToFragment(ReRegistrationFragment.RegistrationMethodChooser)
        }
        return Action.Finish.ToFragment(ReRegistrationFragment.FidoRegistration(EUser()))
    }

    override fun negativeAction(): Action {
        //todo store flag to not show wizard
        return Action.Finish.ToActivity(ReRegistrationActivity.WelcomeScreen)
    }


}