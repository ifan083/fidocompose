package com.example.fidoreregistration.questionnaire.logic

import com.example.fidoreregistration.questionnaire.data.ReRegistration
import com.example.fidoreregistration.questionnaire.data.ReRegistrationFragment
import com.example.fidoreregistration.questionnaire.data.WizardStep
import com.example.fidoreregistration.questionnaire.ui.EUser
import com.example.fidoreregistration.questionnaire.ui.QuestionScreenDescription
import com.example.fidoreregistration.wizard.Action
import com.example.fidoreregistration.wizard.Wizard

class ActivateFaceId : WizardStep {

    override fun destination() = ReRegistration.ActivateFaceId

    override fun screenDescription() = QuestionScreenDescription(
        title = "Enable Face ID",
        subtitle = "Please allow Face ID for the app.",
        posBtnText = "Enable Face ID",
        negBtnText = null,
        canGoBack = true,
        canExit = true,
        imgUrl = QuestionScreenDescription.Img.PERMISSIONS.url
    )

    override fun positiveAction() : Action {
        if (!Util.isUserLoggedIn()) {
            return Action.Finish.ToFragment(ReRegistrationFragment.RegistrationMethodChooser)
        }
        return Action.Finish.ToFragment(ReRegistrationFragment.FidoRegistration(EUser()))
    }

    override fun negativeAction() = Action.Continue(Wizard.NoDestination)
}