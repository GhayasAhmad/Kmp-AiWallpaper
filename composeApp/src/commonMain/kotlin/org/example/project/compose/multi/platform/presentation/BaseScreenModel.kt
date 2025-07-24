package org.example.project.compose.multi.platform.presentation

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

abstract class BaseScreenModel<State : Any, Action, Event>(
    initialState: State
) : ScreenModel {

    private val _viewStates = MutableStateFlow(initialState)
    private val _viewActions = Channel<Action?>(Channel.BUFFERED)

    fun viewStates(): StateFlow<State> = _viewStates.asStateFlow()
    fun viewActions(): Flow<Action?> = _viewActions.receiveAsFlow()

    protected var viewState: State
        get() = _viewStates.value
        set(value) {
            _viewStates.update { value }
        }

    protected var viewAction: Action?
        get() = _viewActions.tryReceive().getOrNull()
        set(value) {
            _viewActions.trySend(value)
        }

    fun clearAction() {
        viewAction = null
    }

    abstract fun obtainEvent(viewEvent: Event)
}