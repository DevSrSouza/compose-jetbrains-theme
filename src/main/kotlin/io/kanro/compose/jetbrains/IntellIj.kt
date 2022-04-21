package io.kanro.compose.jetbrains

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import io.kanro.compose.jetbrains.color.ButtonColors
import io.kanro.compose.jetbrains.color.CheckBoxColors
import io.kanro.compose.jetbrains.color.FieldColors
import io.kanro.compose.jetbrains.color.FocusColors
import io.kanro.compose.jetbrains.color.IconColors
import io.kanro.compose.jetbrains.color.PanelColors
import io.kanro.compose.jetbrains.color.ProgressColors
import io.kanro.compose.jetbrains.color.ScrollColors
import io.kanro.compose.jetbrains.color.SelectionColors
import io.kanro.compose.jetbrains.color.TabColors
import io.kanro.compose.jetbrains.color.TextColors
import io.kanro.compose.jetbrains.color.ToolBarColors
import javax.swing.UIManager
import java.awt.Color as AWTColor
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlin.random.Random

@Composable
fun JBThemeFromIntelliJ(
    content: @Composable () -> Unit
) {
    JBTheme(
        buttonColors = intellijButtonColor(),
        fieldColors = intellijFieldColor(),
        focusColors = intellijFocusColor(),
        panelColors = intellijPanelColors(),
        textColors = intellijTextColors(),
        toolBarColors = intellijToolbarColors(),
        progressColors = intellijProgressColor(),
        scrollColors = intellijScrollColors(),
        tabColors = intellijTabColors(),
        selectionColors = intellijSelectionColors(),
        checkBoxColors = intellijCheckboxColors(),
        iconColors = intellijIconColors(),
        typography = JBTheme.typography,
        iconTheme = JBTheme.iconTheme,
        selectionScope = LocalSelectionScope.current,
    ) {
        content()
    }
}

@Composable
fun swingColor(key: String): Color {
    return remember(SwingColor.themeChangeState) {
        UIManager.getColor(key).asComposeColor
    }
}

@Composable fun intellijButtonColor() = ButtonColors(
    bg = swingColor("Button.default.startBackground"), // TODO: this will always exist?
    border = swingColor("Button.default.startBorderColor"), // TODO: this will always exist?
    borderRegularFocused = swingColor("ActionButton.focusedBorderColor"), //TODO: check this one
    defaultStart = swingColor("Button.default.startBackground"),
    defaultEnd = swingColor("Button.default.endBackground"),
    borderDefaultStart = swingColor("Button.default.startBorderColor"),
    borderDefaultEnd = swingColor("Button.default.endBorderColor"),
    borderDefaultFocused = swingColor("Button.default.focusedBorderColor"),
    bgDisabled = swingColor("Button.default.startBackground"), // TODO: don't know this one, will use Button.default.startBackground for now
    borderDisabled = swingColor("Button.disabledBorderColor"),
)

@Composable fun intellijFieldColor() = FieldColors(
    bg = swingColor("TextField.background"),
    border = swingColor("Component.borderColor"),
    borderFocused = swingColor("Component.focusedBorderColor"),
    comboboxButton = swingColor("ComboBoxButton.background"),
    bgDisabled = swingColor("TextField.disabledBackground"),
    borderDisabled = swingColor("Component.disabledBorderColor"),
    borderError = swingColor("Component.errorFocusColor"), // TODO: try a better one?
)

@Composable fun intellijFocusColor() = FocusColors(
    default = swingColor("Focus.color"),
    error = swingColor("Component.errorFocusColor"),
    warning = swingColor("Component.warningFocusColor"),
    warningInactive = swingColor("Component.inactiveWarningFocusColor"),
)

@Composable fun intellijPanelColors() = PanelColors(
    border = swingColor("Panel.background"),
    bgContent = swingColor("Panel.foreground"),
    bgDialog = swingColor("OptionPane.background"),
)

@Composable fun intellijTextColors() = TextColors(
    default = swingColor("Label.foreground"),
    disabled = swingColor("Label.disabledForeground"),
    white = swingColor("TextField.light"),
    link = swingColor("Link.activeForeground"),
    infoPanel = swingColor("Label.infoForeground"),
    infoInput = swingColor("Label.infoForeground"), // TODO: any better for this one?
    error = swingColor("Label.errorForeground"),
    success = swingColor("Label.successForeground"),
)

@Composable fun intellijToolbarColors() = ToolBarColors(
    buttonHover = swingColor("ActionButton.hoverBackground"),
    buttonPressed = swingColor("ActionButton.pressedBackground"),
    iconSplitBorder = swingColor("ActionButton.hoverBackground"), // TODO: don't know what should I use here
)

@Composable fun intellijProgressColor() = ProgressColors(
    progress = swingColor("ProgressBar.progressColor"),
    bg = swingColor("ProgressBar.background"),
)

@Composable fun intellijScrollColors() = ScrollColors(
    bg = swingColor("ScrollBar.background").copy(alpha = 0.50f)
)

@Composable fun intellijTabColors() = TabColors(
    selection = swingColor("DefaultTabs.underlineColor"),
    focus = swingColor("TabbedPane.focusColor"),
    selectionInactive = swingColor("DefaultTabs.inactiveUnderlineColor"),
    hover = swingColor("DefaultTabs.hoverBackground").copy(alpha = .25f),
    selectionDisabled = swingColor("TabbedPane.disabledUnderlineColor"),
    bgSelected = swingColor("TabbedPane.highlight"),
)

@Composable fun intellijSelectionColors() = SelectionColors(
    active = swingColor("Table.selectionBackground"),
    inactive = swingColor("Table.hoverInactiveBackground"),
    hover = swingColor("Table.hoverBackground"),
    lightActive = swingColor("Table.lightSelectionBackground"),
    lightInactive = swingColor("Table.lightSelectionInactiveBackground"),
    completionPopup = swingColor("CompletionPopup.selectionBackground"),
)

@Composable fun intellijCheckboxColors() = CheckBoxColors(
    bg = swingColor("ComboBox.background"),
    bgSelected = swingColor("ComboBox.selectionBackground"),
    bgDisabled = swingColor("ComboBox.disabledBackground"),
    border = swingColor("Borders.color"), // dont know
    borderSelected = swingColor("ComboBox.selectionBackground"), // dont know
    borderFocused = swingColor("ComboBox.selectionBackground"),
    borderDisabled = swingColor("Component.disabledBorderColor"),
)

@Composable fun intellijIconColors() = IconColors(
    selected = swingColor("Component.iconColor"),
    disabled = swingColor("Button.disabledBorderColor"), // TODO have no idea
)

object SwingColor {
    var themeChangeState by mutableStateOf(Random.nextInt())
        private set

    fun onThemeChange() {
        themeChangeState = Random.nextInt()
    }
}

private val AWTColor.asComposeColor: Color get() = Color(red, green, blue, alpha)