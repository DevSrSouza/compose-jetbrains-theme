package io.kanro.compose.jetbrains

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import io.kanro.compose.jetbrains.color.ButtonColors
import io.kanro.compose.jetbrains.color.lightButtonColors
import io.kanro.compose.jetbrains.color.darkButtonColors
import io.kanro.compose.jetbrains.color.CheckBoxColors
import io.kanro.compose.jetbrains.color.lightCheckBoxColors
import io.kanro.compose.jetbrains.color.darkCheckBoxColors
import io.kanro.compose.jetbrains.color.FieldColors
import io.kanro.compose.jetbrains.color.lightFieldColors
import io.kanro.compose.jetbrains.color.darkFieldColors
import io.kanro.compose.jetbrains.color.FocusColors
import io.kanro.compose.jetbrains.color.lightFocusColors
import io.kanro.compose.jetbrains.color.darkFocusColors
import io.kanro.compose.jetbrains.color.IconColors
import io.kanro.compose.jetbrains.color.PanelColors
import io.kanro.compose.jetbrains.color.lightPanelColors
import io.kanro.compose.jetbrains.color.darkPanelColors
import io.kanro.compose.jetbrains.color.ProgressColors
import io.kanro.compose.jetbrains.color.lightProgressColors
import io.kanro.compose.jetbrains.color.darkProgressColors
import io.kanro.compose.jetbrains.color.ScrollColors
import io.kanro.compose.jetbrains.color.lightScrollColors
import io.kanro.compose.jetbrains.color.darkScrollColors
import io.kanro.compose.jetbrains.color.SelectionColors
import io.kanro.compose.jetbrains.color.lightSelectionColors
import io.kanro.compose.jetbrains.color.darkSelectionColors
import io.kanro.compose.jetbrains.color.TabColors
import io.kanro.compose.jetbrains.color.lightTabColors
import io.kanro.compose.jetbrains.color.darkTabColors
import io.kanro.compose.jetbrains.color.TextColors
import io.kanro.compose.jetbrains.color.lightTextColors
import io.kanro.compose.jetbrains.color.darkTextColors
import io.kanro.compose.jetbrains.color.ToolBarColors
import io.kanro.compose.jetbrains.color.lightToolBarColors
import io.kanro.compose.jetbrains.color.darkToolBarColors
import javax.swing.UIManager
import java.awt.Color as AWTColor
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import io.kanro.compose.jetbrains.color.lightIconColors
import kotlin.math.sqrt
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
fun isIntelliJThemeDark(): Boolean {
    return remember(SwingColor.themeChangeState) {
        val color = swingColor("Panel.background") ?: Color.White

        val brightness = with(color) {
            sqrt(
                red * red * .241f +
                        green * green * .691f +
                        blue * blue * .068f
            )
        }

        brightness < 0.50
    }
}

@Composable
fun swingColor(vararg key: String): Color? {
    return remember(SwingColor.themeChangeState) {
        key.firstNotNullOfOrNull { UIManager.getColor(it) }?.asComposeColor
    }
}

@Composable fun intellijButtonColor() = ButtonColors(
    bg = swingColor("Button.default.startBackground")
        ?: (if(isIntelliJThemeDark()) darkButtonColors() else lightButtonColors()).bg, // TODO: this will always exist?
    border = swingColor("Button.default.startBorderColor")
        ?: (if(isIntelliJThemeDark()) darkButtonColors() else lightButtonColors()).border, // TODO: this will always exist?
    borderRegularFocused = swingColor("ActionButton.focusedBorderColor")
        ?: (if(isIntelliJThemeDark()) darkButtonColors() else lightButtonColors()).borderRegularFocused, //TODO: check this one
    defaultStart = swingColor("Button.default.startBackground")
        ?: (if(isIntelliJThemeDark()) darkButtonColors() else lightButtonColors()).defaultStart,
    defaultEnd = swingColor("Button.default.endBackground")
        ?: (if(isIntelliJThemeDark()) darkButtonColors() else lightButtonColors()).defaultEnd,
    borderDefaultStart = swingColor("Button.default.startBorderColor")
        ?: (if(isIntelliJThemeDark()) darkButtonColors() else lightButtonColors()).borderDefaultStart,
    borderDefaultEnd = swingColor("Button.default.endBorderColor")
        ?: (if(isIntelliJThemeDark()) darkButtonColors() else lightButtonColors()).borderDefaultEnd,
    borderDefaultFocused = swingColor("Button.default.focusedBorderColor")
        ?: (if(isIntelliJThemeDark()) darkButtonColors() else lightButtonColors()).borderDefaultFocused,
    bgDisabled = swingColor("Button.default.startBackground")
        ?: (if(isIntelliJThemeDark()) darkButtonColors() else lightButtonColors()).bgDisabled, // TODO: don't know this one, will use Button.default.startBackground for now
    borderDisabled = swingColor("Button.disabledBorderColor")
        ?: (if(isIntelliJThemeDark()) darkButtonColors() else lightButtonColors()).borderDisabled,
)

@Composable fun intellijFieldColor() = FieldColors(
    bg = swingColor("TextField.background")
        ?: (if(isIntelliJThemeDark()) darkFieldColors() else lightFieldColors()).bg,
    border = swingColor("Component.borderColor", "TextField.borderColor")
        ?: (if(isIntelliJThemeDark()) darkFieldColors() else lightFieldColors()).border,
    borderFocused = swingColor("Component.focusedBorderColor", "Button.focusedBorderColor")
        ?: (if(isIntelliJThemeDark()) darkFieldColors() else lightFieldColors()).borderFocused,
    comboboxButton = swingColor("ComboBoxButton.background")
        ?: (if(isIntelliJThemeDark()) darkFieldColors() else lightFieldColors()).comboboxButton,
    bgDisabled = swingColor("TextField.disabledBackground", "ComboBox.disabledBackground")
        ?: (if(isIntelliJThemeDark()) darkFieldColors() else lightFieldColors()).bgDisabled,
    borderDisabled = swingColor("Component.disabledBorderColor", "Button.disabledBorderColor")
        ?: (if(isIntelliJThemeDark()) darkFieldColors() else lightFieldColors()).borderDisabled,
    borderError = swingColor("Component.errorFocusColor")
        ?: (if(isIntelliJThemeDark()) darkFieldColors() else lightFieldColors()).borderError, // TODO: try a better one?
)

@Composable fun intellijFocusColor() = FocusColors(
    default = swingColor("Component.focusColor", "Focus.color")
        ?: (if(isIntelliJThemeDark()) darkFocusColors() else lightFocusColors()).default,
    error = swingColor("Component.errorFocusColor")
        ?: (if(isIntelliJThemeDark()) darkFocusColors() else lightFocusColors()).error,
    warning = swingColor("Component.warningFocusColor")
        ?: (if(isIntelliJThemeDark()) darkFocusColors() else lightFocusColors()).warning,
    warningInactive = swingColor("Component.inactiveWarningFocusColor")
        ?: (if(isIntelliJThemeDark()) darkFocusColors() else lightFocusColors()).warningInactive,
)

@Composable fun intellijPanelColors() = PanelColors(
    border = swingColor("ColorPalette.border", "Borders.color", "Panel.background")
        ?: (if(isIntelliJThemeDark()) darkPanelColors() else lightPanelColors()).border,
    bgContent = swingColor("ColorPalette.contentBackground", "List.background", "EditorPane.background")
        ?: (if(isIntelliJThemeDark()) darkPanelColors() else lightPanelColors()).bgContent,
    bgDialog = swingColor("PopupMenu.background", "Panel.background", "OptionPane.background")
        ?: (if(isIntelliJThemeDark()) darkPanelColors() else lightPanelColors()).bgDialog,
)

@Composable fun intellijTextColors() = TextColors(
    default = swingColor("Label.foreground")
        ?: (if(isIntelliJThemeDark()) darkTextColors() else lightTextColors()).default,
    disabled = swingColor("Label.disabledForeground", "Label.disabledText", "Button.disabledText")
        ?: (if(isIntelliJThemeDark()) darkTextColors() else lightTextColors()).disabled,
    white = swingColor("ColorPalette.white", "TextField.light", "Button.light")
        ?: (if(isIntelliJThemeDark()) darkTextColors() else lightTextColors()).white,
    link = swingColor("Link.activeForeground", "ColorPalette.linkForeground")
        ?: (if(isIntelliJThemeDark()) darkTextColors() else lightTextColors()).link,
    infoPanel = swingColor("Label.infoForeground", "ColorPalette.infoPanelForeground")
        ?: (if(isIntelliJThemeDark()) darkTextColors() else lightTextColors()).infoPanel,
    infoInput = swingColor("Component.infoForeground", "ColorPalette.separatorForeground")
        ?: (if(isIntelliJThemeDark()) darkTextColors() else lightTextColors()).infoInput, // TODO: any better for this one?
    error = swingColor("Label.errorForeground", "Notification.errorForeground")
        ?: (if(isIntelliJThemeDark()) darkTextColors() else lightTextColors()).error,
    success = swingColor("Label.successForeground")
        ?: (if(isIntelliJThemeDark()) darkTextColors() else lightTextColors()).success,
)

@Composable fun intellijToolbarColors() = ToolBarColors(
    buttonHover = swingColor("ActionButton.hoverBackground")
        ?: (if(isIntelliJThemeDark()) darkToolBarColors() else lightToolBarColors()).buttonHover,
    buttonPressed = swingColor("ActionButton.pressedBackground")
        ?: (if(isIntelliJThemeDark()) darkToolBarColors() else lightToolBarColors()).buttonPressed,
    iconSplitBorder = swingColor("ActionButton.hoverBackground")
        ?: (if(isIntelliJThemeDark()) darkToolBarColors() else lightToolBarColors()).iconSplitBorder, // TODO: don't know what should I use here
)

@Composable fun intellijProgressColor() = ProgressColors(
    progress = swingColor("ProgressBar.progressColor")
        ?: (if(isIntelliJThemeDark()) darkProgressColors() else lightProgressColors()).progress,
    bg = swingColor("ProgressBar.background")
        ?: (if(isIntelliJThemeDark()) darkProgressColors() else lightProgressColors()).bg,
)

@Composable fun intellijScrollColors() = ScrollColors(
    bg = swingColor("ScrollBar.background")
        ?: (if(isIntelliJThemeDark()) darkScrollColors() else lightScrollColors()).bg
)

@Composable fun intellijTabColors() = TabColors(
    selection = swingColor("DefaultTabs.underlineColor", "TabbedPane.underlineColor")
        ?: (if(isIntelliJThemeDark()) darkTabColors() else lightTabColors()).selection,
    focus = swingColor("TabbedPane.focusColor")
        ?: (if(isIntelliJThemeDark()) darkTabColors() else lightTabColors()).focus,
    selectionInactive = swingColor("DefaultTabs.inactiveUnderlineColor")
        ?: (if(isIntelliJThemeDark()) darkTabColors() else lightTabColors()).selectionInactive,
    hover = swingColor("DefaultTabs.hoverBackground")?.copy(alpha = .25f)
        ?: (if(isIntelliJThemeDark()) darkTabColors() else lightTabColors()).hover,
    selectionDisabled = swingColor("TabbedPane.disabledUnderlineColor")
        ?: (if(isIntelliJThemeDark()) darkTabColors() else lightTabColors()).selectionDisabled,
    bgSelected = swingColor("TabbedPane.highlight", "controlHighlight")
        ?: (if(isIntelliJThemeDark()) darkTabColors() else lightTabColors()).bgSelected,
)

@Composable fun intellijSelectionColors() = SelectionColors(
    active = swingColor("Table.selectionBackground", "List.selectionBackground")
        ?: (if(isIntelliJThemeDark()) darkSelectionColors() else lightSelectionColors()).active,
    inactive = swingColor("Table.hoverInactiveBackground")
        ?: (if(isIntelliJThemeDark()) darkSelectionColors() else lightSelectionColors()).inactive,
    hover = swingColor("Table.hoverBackground", "List.hoverBackground", "Tree.hoverBackground")
        ?: (if(isIntelliJThemeDark()) darkSelectionColors() else lightSelectionColors()).hover,
    lightActive = swingColor("Table.lightSelectionBackground", "Plugins.lightSelectionBackground")
        ?: (if(isIntelliJThemeDark()) darkSelectionColors() else lightSelectionColors()).lightActive,
    lightInactive = swingColor("Table.lightSelectionInactiveBackground")
        ?: (if(isIntelliJThemeDark()) darkSelectionColors() else lightSelectionColors()).lightInactive,
    completionPopup = swingColor("CompletionPopup.selectionBackground")
        ?: (if(isIntelliJThemeDark()) darkSelectionColors() else lightSelectionColors()).completionPopup,
)

@Composable fun intellijCheckboxColors() = CheckBoxColors(
    bg = swingColor("ComboBox.background")
        ?: (if(isIntelliJThemeDark()) darkCheckBoxColors() else lightCheckBoxColors()).bg,
    bgSelected = swingColor("ComboBox.selectionBackground")
        ?: (if(isIntelliJThemeDark()) darkCheckBoxColors() else lightCheckBoxColors()).bgSelected,
    bgDisabled = swingColor("ComboBox.disabledBackground")
        ?: (if(isIntelliJThemeDark()) darkCheckBoxColors() else lightCheckBoxColors()).bgDisabled,
    border = swingColor("Borders.color")
        ?: (if(isIntelliJThemeDark()) darkCheckBoxColors() else lightCheckBoxColors()).border, // dont know
    borderSelected = swingColor("ComboBox.selectionBackground")
        ?: (if(isIntelliJThemeDark()) darkCheckBoxColors() else lightCheckBoxColors()).borderSelected, // dont know
    borderFocused = swingColor("ComboBox.selectionBackground")
        ?: (if(isIntelliJThemeDark()) darkCheckBoxColors() else lightCheckBoxColors()).borderFocused,
    borderDisabled = swingColor("Component.disabledBorderColor")
        ?: (if(isIntelliJThemeDark()) darkCheckBoxColors() else lightCheckBoxColors()).borderDisabled,
)

@Composable fun intellijIconColors() = IconColors(
    selected = swingColor("Component.iconColor") ?: lightIconColors().selected,
    disabled = swingColor("Button.disabledBorderColor") ?: lightIconColors().disabled, // TODO have no idea
)

object SwingColor {
    var themeChangeState by mutableStateOf(Random.nextInt())
        private set

    fun onThemeChange() {
        themeChangeState = Random.nextInt()
    }
}

private val AWTColor.asComposeColor: Color get() = Color(red, green, blue, alpha)