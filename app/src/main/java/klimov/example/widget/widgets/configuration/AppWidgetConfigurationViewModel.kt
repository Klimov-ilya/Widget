package klimov.example.widget.widgets.configuration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AppWidgetConfigurationViewModel : ViewModel() {
    private val _viewState: MutableStateFlow<AppWidgetConfigurationState> by lazy {
        MutableStateFlow(AppWidgetConfigurationState())
    }
    val viewState: Flow<AppWidgetConfigurationState> get() = _viewState

    private val _viewCommand = MutableSharedFlow<AppWidgetSaveConfigurationCommand>()
    val viewCommand: Flow<AppWidgetSaveConfigurationCommand> get() = _viewCommand

    fun setDynamicColorsChecked(isDynamicColorsChecked: Boolean) {
        _viewState.value = _viewState.value.copy(dynamicColorChecked = isDynamicColorsChecked)
    }

    fun saveConfig() {
        viewModelScope.launch {
            val isDynamicColorsChecked = _viewState.value.dynamicColorChecked
            _viewCommand.emit(AppWidgetSaveConfigurationCommand(isDynamicColorsChecked))
        }
    }
}