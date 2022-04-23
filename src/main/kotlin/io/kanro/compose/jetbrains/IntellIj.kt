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
    val color = swingColor("Panel.background") ?: Color.White
    return remember(SwingColor.themeChangeState) {
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

@Composable fun intellijButtonColor(): ButtonColors {
    val fallbackColors = if(isIntelliJThemeDark()) darkButtonColors() else lightButtonColors()

    return ButtonColors(
        bg = swingColor("Button.default.startBackground")
            ?: fallbackColors.bg, // TODO: this will always exist?
        border = swingColor("Button.default.startBorderColor")
            ?: fallbackColors.border, // TODO: this will always exist?
        borderRegularFocused = swingColor("ActionButton.focusedBorderColor")
            ?: fallbackColors.borderRegularFocused, //TODO: check this one
        defaultStart = swingColor("Button.default.startBackground")
            ?: fallbackColors.defaultStart,
        defaultEnd = swingColor("Button.default.endBackground")
            ?: fallbackColors.defaultEnd,
        borderDefaultStart = swingColor("Button.default.startBorderColor")
            ?: fallbackColors.borderDefaultStart,
        borderDefaultEnd = swingColor("Button.default.endBorderColor")
            ?: fallbackColors.borderDefaultEnd,
        borderDefaultFocused = swingColor("Button.default.focusedBorderColor")
            ?: fallbackColors.borderDefaultFocused,
        bgDisabled = swingColor("Button.default.startBackground")
            ?: fallbackColors.bgDisabled, // TODO: don't know this one, will use Button.default.startBackground for now
        borderDisabled = swingColor("Button.disabledBorderColor")
            ?: fallbackColors.borderDisabled,
    )
}

@Composable fun intellijFieldColor(): FieldColors {
    val fallbackColors = if(isIntelliJThemeDark()) darkFieldColors() else lightFieldColors()
    return FieldColors(
        bg = swingColor("TextField.background")
            ?: fallbackColors.bg,
        border = swingColor("Component.borderColor", "TextField.borderColor")
            ?: fallbackColors.border,
        borderFocused = swingColor("Component.focusedBorderColor", "Button.focusedBorderColor")
            ?: fallbackColors.borderFocused,
        comboboxButton = swingColor("ComboBoxButton.background")
            ?: fallbackColors.comboboxButton,
        bgDisabled = swingColor("TextField.disabledBackground", "ComboBox.disabledBackground")
            ?: fallbackColors.bgDisabled,
        borderDisabled = swingColor("Component.disabledBorderColor", "Button.disabledBorderColor")
            ?: fallbackColors.borderDisabled,
        borderError = swingColor("Component.errorFocusColor")
            ?: fallbackColors.borderError, // TODO: try a better one?
    )
}

@Composable fun intellijFocusColor(): FocusColors {
    val fallbackColors = if(isIntelliJThemeDark()) darkFocusColors() else lightFocusColors()

    return FocusColors(
        default = swingColor("Component.focusColor", "Focus.color")
            ?: fallbackColors.default,
        error = swingColor("Component.errorFocusColor")
            ?: fallbackColors.error,
        warning = swingColor("Component.warningFocusColor")
            ?: fallbackColors.warning,
        warningInactive = swingColor("Component.inactiveWarningFocusColor")
            ?: fallbackColors.warningInactive,
    )
}

@Composable fun intellijPanelColors(): PanelColors {
    val fallbackColors = if(isIntelliJThemeDark()) darkPanelColors() else lightPanelColors()

    return PanelColors(
        border = swingColor("ColorPalette.border", "Borders.color", "Panel.background")
            ?: fallbackColors.border,
        bgContent = swingColor("ColorPalette.contentBackground", "List.background", "EditorPane.background")
            ?: fallbackColors.bgContent,
        bgDialog = swingColor("PopupMenu.background", "Panel.background", "OptionPane.background")
            ?: fallbackColors.bgDialog,
    )
}

@Composable fun intellijTextColors(): TextColors {
    val fallbackColors = if(isIntelliJThemeDark()) darkTextColors() else lightTextColors()

    return TextColors(
        default = swingColor("Label.foreground")
            ?: fallbackColors.default,
        disabled = swingColor("Label.disabledForeground", "Label.disabledText", "Button.disabledText")
            ?: fallbackColors.disabled,
        white = swingColor("ColorPalette.white", "TextField.light", "Button.light")
            ?: fallbackColors.white,
        link = swingColor("Link.activeForeground", "ColorPalette.linkForeground")
            ?: fallbackColors.link,
        infoPanel = swingColor("Label.infoForeground", "ColorPalette.infoPanelForeground")
            ?: fallbackColors.infoPanel,
        infoInput = swingColor("Component.infoForeground", "ColorPalette.separatorForeground")
            ?: fallbackColors.infoInput, // TODO: any better for this one?
        error = swingColor("Label.errorForeground", "Notification.errorForeground")
            ?: fallbackColors.error,
        success = swingColor("Label.successForeground")
            ?: fallbackColors.success,
    )
}

@Composable fun intellijToolbarColors(): ToolBarColors {
    val fallbackColors = if(isIntelliJThemeDark()) darkToolBarColors() else lightToolBarColors()

    return ToolBarColors(
        buttonHover = swingColor("ActionButton.hoverBackground")
            ?: fallbackColors.buttonHover,
        buttonPressed = swingColor("ActionButton.pressedBackground")
            ?: fallbackColors.buttonPressed,
        iconSplitBorder = swingColor("ActionButton.hoverBackground")
            ?: fallbackColors.iconSplitBorder, // TODO: don't know what should I use here
    )
}

@Composable fun intellijProgressColor(): ProgressColors {
    val fallbackColors = if(isIntelliJThemeDark()) darkProgressColors() else lightProgressColors()

    return ProgressColors(
        progress = swingColor("ProgressBar.progressColor")
            ?: fallbackColors.progress,
        bg = swingColor("ProgressBar.background")
            ?: fallbackColors.bg,
    )
}

@Composable fun intellijScrollColors(): ScrollColors {
    val fallbackColors = if(isIntelliJThemeDark()) darkScrollColors() else lightScrollColors()

    return ScrollColors(
        bg = swingColor("ScrollBar.background")
            ?: fallbackColors.bg
    )
}

@Composable fun intellijTabColors(): TabColors {
    val fallbackColors = if(isIntelliJThemeDark()) darkTabColors() else lightTabColors()

    return TabColors(
        selection = swingColor("DefaultTabs.underlineColor", "TabbedPane.underlineColor")
            ?: fallbackColors.selection,
        focus = swingColor("TabbedPane.focusColor")
            ?: fallbackColors.focus,
        selectionInactive = swingColor("DefaultTabs.inactiveUnderlineColor")
            ?: fallbackColors.selectionInactive,
        hover = swingColor("DefaultTabs.hoverBackground")?.copy(alpha = .25f)
            ?: fallbackColors.hover,
        selectionDisabled = swingColor("TabbedPane.disabledUnderlineColor")
            ?: fallbackColors.selectionDisabled,
        bgSelected = swingColor("TabbedPane.highlight", "controlHighlight")
            ?: fallbackColors.bgSelected,
    )
}


@Composable fun intellijSelectionColors(): SelectionColors {
    val fallbackColors = if(isIntelliJThemeDark()) darkSelectionColors() else lightSelectionColors()

    return SelectionColors(
        active = swingColor("Table.selectionBackground", "List.selectionBackground")
            ?: fallbackColors.active,
        inactive = swingColor("Table.hoverInactiveBackground")
            ?: fallbackColors.inactive,
        hover = swingColor("Table.hoverBackground", "List.hoverBackground", "Tree.hoverBackground")
            ?: fallbackColors.hover,
        lightActive = swingColor("Table.lightSelectionBackground", "Plugins.lightSelectionBackground")
            ?: fallbackColors.lightActive,
        lightInactive = swingColor("Table.lightSelectionInactiveBackground")
            ?: fallbackColors.lightInactive,
        completionPopup = swingColor("CompletionPopup.selectionBackground")
            ?: fallbackColors.completionPopup,
    )
}

@Composable fun intellijCheckboxColors(): CheckBoxColors {
    val fallbackColors = if(isIntelliJThemeDark()) darkCheckBoxColors() else lightCheckBoxColors()

    return CheckBoxColors(
        bg = swingColor("ComboBox.background")
            ?: fallbackColors.bg,
        bgSelected = swingColor("ComboBox.selectionBackground")
            ?: fallbackColors.bgSelected,
        bgDisabled = swingColor("ComboBox.disabledBackground")
            ?: fallbackColors.bgDisabled,
        border = swingColor("Borders.color")
            ?: fallbackColors.border, // dont know
        borderSelected = swingColor("ComboBox.selectionBackground")
            ?: fallbackColors.borderSelected, // dont know
        borderFocused = swingColor("ComboBox.selectionBackground")
            ?: fallbackColors.borderFocused,
        borderDisabled = swingColor("Component.disabledBorderColor")
            ?: fallbackColors.borderDisabled,
    )
}

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