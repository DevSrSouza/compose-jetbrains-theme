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
import io.kanro.compose.jetbrains.JBTheme
import io.kanro.compose.jetbrains.LocalSelectionScope
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
fun swingColor(vararg key: String): Color? {
    return remember(SwingColor.themeChangeState) {
        key.firstNotNullOfOrNull { UIManager.getColor(it) }?.asComposeColor
    }
}

@Composable fun intellijButtonColor() = ButtonColors(
    bg = swingColor("Button.default.startBackground") ?: Color.White, // TODO: this will always exist?
    border = swingColor("Button.default.startBorderColor") ?: Color(0xFFC4C4C4), // TODO: this will always exist?
    borderRegularFocused = swingColor("ActionButton.focusedBorderColor") ?: Color(0xFF87AFDA), //TODO: check this one
    defaultStart = swingColor("Button.default.startBackground") ?: Color(0xFF528CC7),
    defaultEnd = swingColor("Button.default.endBackground") ?: Color(0xFF4989CC),
    borderDefaultStart = swingColor("Button.default.startBorderColor") ?: Color(0xFF487EB8),
    borderDefaultEnd = swingColor("Button.default.endBorderColor") ?: Color(0xFF346DAD),
    borderDefaultFocused = swingColor("Button.default.focusedBorderColor") ?: Color(0xFFA8CEF6),
    bgDisabled = swingColor("Button.default.startBackground") ?: Color(0xFFF2F2F2), // TODO: don't know this one, will use Button.default.startBackground for now
    borderDisabled = swingColor("Button.disabledBorderColor") ?: Color(0xFFCFCFCF),
)

@Composable fun intellijFieldColor() = FieldColors(
    bg = swingColor("TextField.background") ?: Color.White,
    border = swingColor("Component.borderColor", "TextField.borderColor") ?: Color(0xFFC4C4C4),
    borderFocused = swingColor("Component.focusedBorderColor", "Button.focusedBorderColor") ?: Color(0xFF87AFDA),
    comboboxButton = swingColor("ComboBoxButton.background") ?: Color(0xFFFAFAFA),
    bgDisabled = swingColor("TextField.disabledBackground", "ComboBox.disabledBackground") ?: Color(0xFFF2F2F2),
    borderDisabled = swingColor("Component.disabledBorderColor", "Button.disabledBorderColor") ?: Color(0xFFCFCFCF),
    borderError = swingColor("Component.errorFocusColor") ?: Color(0xFFCE3845), // TODO: try a better one?
)

@Composable fun intellijFocusColor() = FocusColors(
    default = swingColor("Component.focusColor", "Focus.color") ?: Color(0xFF97C3F3),
    error = swingColor("Component.errorFocusColor") ?: Color(0xFFE53E4D),
    warning = swingColor("Component.warningFocusColor") ?: Color(0xFFE1A336),
    warningInactive = swingColor("Component.inactiveWarningFocusColor") ?: Color(0xFFEAD2A1),
)

@Composable fun intellijPanelColors() = PanelColors(
    border = swingColor("ColorPalette.border", "Borders.color", "Panel.background") ?: Color(0xFFD1D1D1),
    bgContent = swingColor("ColorPalette.contentBackground", "List.background", "EditorPane.background") ?: Color.White,
    bgDialog = swingColor("PopupMenu.background", "Panel.background", "OptionPane.background") ?: Color(0xFFF2F2F2),
)

@Composable fun intellijTextColors() = TextColors(
    default = swingColor("Label.foreground") ?: Color.Black,
    disabled = swingColor("Label.disabledForeground", "Label.disabledText", "Button.disabledText") ?: Color(0xFF8C8C8C),
    white = swingColor("ColorPalette.white", "TextField.light", "Button.light") ?: Color.White,
    link = swingColor("Link.activeForeground", "ColorPalette.linkForeground") ?: Color(0xFF2470B3),
    infoPanel = swingColor("Label.infoForeground", "ColorPalette.infoPanelForeground") ?: Color(0xFF808080),
    infoInput = swingColor("Component.infoForeground", "ColorPalette.separatorForeground") ?: Color(0xFF999999), // TODO: any better for this one?
    error = swingColor("Label.errorForeground", "Notification.errorForeground") ?: Color(0xFFC7222D),
    success = swingColor("Label.successForeground") ?: Color(0xFF368746),
)

@Composable fun intellijToolbarColors() = ToolBarColors(
    buttonHover = swingColor("ActionButton.hoverBackground") ?: Color(0xFFCFCFCF),
    buttonPressed = swingColor("ActionButton.pressedBackground") ?: Color(0xFFDFDFDF),
    iconSplitBorder = swingColor("ActionButton.hoverBackground") ?: Color(0xFFB3B3B3), // TODO: don't know what should I use here
)

@Composable fun intellijProgressColor() = ProgressColors(
    progress = swingColor("ProgressBar.progressColor") ?: Color(0xFF1E82E6),
    bg = swingColor("ProgressBar.background") ?: Color(0xFFD5D5D5),
)

@Composable fun intellijScrollColors() = ScrollColors(
    bg = (swingColor("ScrollBar.background") ?: Color(0xFFC9C9C9)).copy(alpha = 0.50f)
)

@Composable fun intellijTabColors() = TabColors(
    selection = swingColor("DefaultTabs.underlineColor", "TabbedPane.underlineColor") ?: Color(0xFF4083C9),
    focus = swingColor("TabbedPane.focusColor") ?: Color(0xFFDAE4ED),
    selectionInactive = swingColor("DefaultTabs.inactiveUnderlineColor") ?: Color(0xFF9CA7B8),
    hover = swingColor("DefaultTabs.hoverBackground")?.copy(alpha = .25f) ?: Color(0x19000000),
    selectionDisabled = swingColor("TabbedPane.disabledUnderlineColor") ?: Color(0xFFABABAB),
    bgSelected = swingColor("TabbedPane.highlight", "controlHighlight") ?: Color(0xFFFFFFFF),
)

@Composable fun intellijSelectionColors() = SelectionColors(
    active = swingColor("Table.selectionBackground", "List.selectionBackground") ?: Color(0xFF2675BF),
    inactive = swingColor("Table.hoverInactiveBackground") ?: Color(0xFFD5D5D5),
    hover = swingColor("Table.hoverBackground", "List.hoverBackground", "Tree.hoverBackground") ?: Color(0xFFEDF5FC),
    lightActive = swingColor("Table.lightSelectionBackground", "Plugins.lightSelectionBackground") ?: Color(0xFFEDF6FE),
    lightInactive = swingColor("Table.lightSelectionInactiveBackground") ?: Color(0xFFF5F5F5),
    completionPopup = swingColor("CompletionPopup.selectionBackground") ?: Color(0xFFC5DFFC),
)

@Composable fun intellijCheckboxColors() = CheckBoxColors(
    bg = swingColor("ComboBox.background") ?: Color.White,
    bgSelected = swingColor("ComboBox.selectionBackground") ?: Color(0xFF4F9EE3),
    bgDisabled = swingColor("ComboBox.disabledBackground") ?: Color(0xFFF2F2F2),
    border = swingColor("Borders.color") ?: Color(0xFFB0B0B0), // dont know
    borderSelected = swingColor("ComboBox.selectionBackground") ?: Color(0xFF4B97D9), // dont know
    borderFocused = swingColor("ComboBox.selectionBackground") ?: Color(0xFF7B9FC7),
    borderDisabled = swingColor("Component.disabledBorderColor") ?: Color(0xFFBDBDBD),
)

@Composable fun intellijIconColors() = IconColors(
    selected = swingColor("Component.iconColor") ?: Color.White,
    disabled = swingColor("Button.disabledBorderColor") ?: Color(0xFFABABAB), // TODO have no idea
)

object SwingColor {
    var themeChangeState by mutableStateOf(Random.nextInt())
        private set

    fun onThemeChange() {
        themeChangeState = Random.nextInt()
    }
}

private val AWTColor.asComposeColor: Color get() = Color(red, green, blue, alpha)