package com.example.fidoreregistration.wizard

object Wizard {
    interface Entry

    interface Destination
    object NoDestination : Destination

    interface FragmentDestination
    object NoFragmentDestination : FragmentDestination

    interface ActivityDestination
    object NoActivityDestination : ActivityDestination
}

sealed class Action {
    class Continue(val destination: Wizard.Destination) : Action()
    sealed class Finish : Action() {
        class ToFragment(val fragmentDestination: Wizard.FragmentDestination) :
            Finish()

        class ToActivity(val activityDestination: Wizard.ActivityDestination) :
            Finish()
    }
}