package io.kanro.compose.jetbrains

import androidx.compose.foundation.LocalScrollbarStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import io.kanro.compose.jetbrains.color.ButtonColors
import io.kanro.compose.jetbrains.color.CheckBoxColors
import io.kanro.compose.jetbrains.color.FieldColors
import io.kanro.compose.jetbrains.color.FocusColors
import io.kanro.compose.jetbrains.color.IconColors
import io.kanro.compose.jetbrains.color.LocalButtonColors
import io.kanro.compose.jetbrains.color.LocalCheckBoxColors
import io.kanro.compose.jetbrains.color.LocalFieldColors
import io.kanro.compose.jetbrains.color.LocalFocusColors
import io.kanro.compose.jetbrains.color.LocalIconColors
import io.kanro.compose.jetbrains.color.LocalPanelColors
import io.kanro.compose.jetbrains.color.LocalProgressColors
import io.kanro.compose.jetbrains.color.LocalScrollColors
import io.kanro.compose.jetbrains.color.LocalSelectionColors
import io.kanro.compose.jetbrains.color.LocalTabColors
import io.kanro.compose.jetbrains.color.LocalTextColors
import io.kanro.compose.jetbrains.color.LocalToolBarColors
import io.kanro.compose.jetbrains.color.PanelColors
import io.kanro.compose.jetbrains.color.ProgressColors
import io.kanro.compose.jetbrains.color.ScrollColors
import io.kanro.compose.jetbrains.color.SelectionColors
import io.kanro.compose.jetbrains.color.TabColors
import io.kanro.compose.jetbrains.color.TextColors
import io.kanro.compose.jetbrains.color.ToolBarColors
import io.kanro.compose.jetbrains.color.darkButtonColors
import io.kanro.compose.jetbrains.color.darkCheckBoxColors
import io.kanro.compose.jetbrains.color.darkFieldColors
import io.kanro.compose.jetbrains.color.darkFocusColors
import io.kanro.compose.jetbrains.color.darkPanelColors
import io.kanro.compose.jetbrains.color.darkProgressColors
import io.kanro.compose.jetbrains.color.darkScrollColors
import io.kanro.compose.jetbrains.color.darkSelectionColors
import io.kanro.compose.jetbrains.color.darkTabColors
import io.kanro.compose.jetbrains.color.darkTextColors
import io.kanro.compose.jetbrains.color.darkToolBarColors
import io.kanro.compose.jetbrains.color.lightButtonColors
import io.kanro.compose.jetbrains.color.lightCheckBoxColors
import io.kanro.compose.jetbrains.color.lightFieldColors
import io.kanro.compose.jetbrains.color.lightFocusColors
import io.kanro.compose.jetbrains.color.lightIconColors
import io.kanro.compose.jetbrains.color.lightPanelColors
import io.kanro.compose.jetbrains.color.lightProgressColors
import io.kanro.compose.jetbrains.color.lightScrollColors
import io.kanro.compose.jetbrains.color.lightSelectionColors
import io.kanro.compose.jetbrains.color.lightTabColors
import io.kanro.compose.jetbrains.color.lightTextColors
import io.kanro.compose.jetbrains.color.lightToolBarColors
import io.kanro.compose.jetbrains.control.LocalContentColor
import javax.swing.UIManager
import kotlin.math.sqrt
import kotlin.random.Random

@Composable
fun JBThemeFromIntelliJ(
    typography: JBTypography = JBTheme.typography,
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
        typography = typography,
        iconTheme = JBTheme.iconTheme,
        selectionScope = LocalSelectionScope.current,
    ) {
        val buttonColors = LocalButtonColors.current
        val fieldColors = LocalFieldColors.current
        val focusColors = LocalFocusColors.current
        val panelColors = LocalPanelColors.current
        val textColors = LocalTextColors.current
        val toolbarColors = LocalToolBarColors.current
        val progressColors = LocalProgressColors.current
        val scrollColors = LocalScrollColors.current
        val tabColors = LocalTabColors.current
        val selectionColors = LocalSelectionColors.current
        val checkBoxColors = LocalCheckBoxColors.current
        val iconColors = LocalIconColors.current
        val typography = LocalTypography.current
        val iconTheme = LocalIconTheme.current
        val selectionScope = LocalSelectionScope.current
        val scrollBarStyle = LocalScrollbarStyle.current
        val contentColor = LocalContentColor.current

        remember(SwingColor.themeChangeState) {
            buttonColors.updateWithIntelliJColor()
            fieldColors.updateWithIntelliJColor()
            focusColors.updateWithIntelliJColor()
            panelColors.updateWithIntelliJColor()
            textColors.updateWithIntelliJColor()
            toolbarColors.updateWithIntelliJColor()
            progressColors.updateWithIntelliJColor()
            scrollColors.updateWithIntelliJColor()
            tabColors.updateWithIntelliJColor()
            selectionColors.updateWithIntelliJColor()
            checkBoxColors.updateWithIntelliJColor()
            iconColors.updateWithIntelliJColor()
            // TODO: typograph
        }
        content()
    }
}

@Composable
fun isIntelliJThemeDark(): Boolean {
    return remember(SwingColor.themeChangeState) {
        val color = getSwingColor("Panel.background") ?: Color.White
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
        getSwingColor(*key)
    }
}

private fun getSwingColor(vararg key: String): Color? {
    return key.firstNotNullOfOrNull { UIManager.getColor(it) }?.asComposeColor
}

fun ButtonColors.updateWithIntelliJColor() {
    bg = getSwingColor("Button.default.startBackground") ?: bg
    border = getSwingColor("Button.default.startBorderColor") ?: border
    borderRegularFocused = getSwingColor("ActionButton.focusedBorderColor") ?: borderRegularFocused
    defaultStart = getSwingColor("Button.default.startBackground") ?: defaultStart
    defaultEnd = getSwingColor("Button.default.endBackground") ?: defaultEnd
    borderDefaultStart = getSwingColor("Button.default.startBorderColor") ?: borderDefaultStart
    borderDefaultEnd = getSwingColor("Button.default.endBorderColor") ?: borderDefaultEnd
    borderDefaultFocused = getSwingColor("Button.default.focusedBorderColor") ?: borderDefaultFocused
    bgDisabled = getSwingColor("Button.default.startBackground") ?: bgDisabled
    borderDisabled = getSwingColor("Button.disabledBorderColor") ?: borderDisabled
}

@Composable
fun intellijButtonColor(): ButtonColors {
    val fallbackColors = if(isIntelliJThemeDark()) darkButtonColors() else lightButtonColors()

    return fallbackColors.apply { updateWithIntelliJColor() }
}

fun FieldColors.updateWithIntelliJColor() {
    bg = getSwingColor("TextField.background") ?: bg
    border = getSwingColor("Component.borderColor", "TextField.borderColor") ?: border
    borderFocused = getSwingColor("Component.focusedBorderColor", "Button.focusedBorderColor") ?: borderFocused
    comboboxButton = getSwingColor("ComboBoxButton.background") ?: comboboxButton
    bgDisabled = getSwingColor("TextField.disabledBackground", "ComboBox.disabledBackground") ?: bgDisabled
    borderDisabled = getSwingColor("Component.disabledBorderColor", "Button.disabledBorderColor") ?: borderDisabled
    borderError = getSwingColor("Component.errorFocusColor") ?: borderError
}

@Composable
fun intellijFieldColor(): FieldColors {
    val fallbackColors = if(isIntelliJThemeDark()) darkFieldColors() else lightFieldColors()
    return fallbackColors.apply { updateWithIntelliJColor() }
}

fun FocusColors.updateWithIntelliJColor() {
    default = getSwingColor("Component.focusColor", "Focus.color") ?: default
    error = getSwingColor("Component.errorFocusColor") ?: error
    warning = getSwingColor("Component.warningFocusColor") ?: warning
    warningInactive = getSwingColor("Component.inactiveWarningFocusColor") ?: warningInactive
}

@Composable
fun intellijFocusColor(): FocusColors {
    val fallbackColors = if(isIntelliJThemeDark()) darkFocusColors() else lightFocusColors()

    return fallbackColors.apply { updateWithIntelliJColor() }
}

fun PanelColors.updateWithIntelliJColor() {
    border = getSwingColor("ColorPalette.border", "Borders.color", "Panel.background") ?: border
    bgContent = getSwingColor("ColorPalette.contentBackground", "List.background", "EditorPane.background") ?: bgContent
    bgDialog = getSwingColor("PopupMenu.background", "Panel.background", "OptionPane.background") ?: bgDialog
}

@Composable
fun intellijPanelColors(): PanelColors {
    val fallbackColors = if(isIntelliJThemeDark()) darkPanelColors() else lightPanelColors()

    return fallbackColors.apply { updateWithIntelliJColor() }
}

fun TextColors.updateWithIntelliJColor() {
    default = getSwingColor("Label.foreground") ?: default
    disabled = getSwingColor("Label.disabledForeground", "Label.disabledText", "Button.disabledText") ?: disabled
    selected = getSwingColor("Button.default.foreground") ?: selected
    link = getSwingColor("Link.activeForeground", "ColorPalette.linkForeground") ?: link
    infoPanel = getSwingColor("Label.infoForeground", "ColorPalette.infoPanelForeground") ?: infoPanel
    infoInput = getSwingColor("Component.infoForeground", "ColorPalette.separatorForeground") ?: infoInput
    error = getSwingColor("Label.errorForeground", "Notification.errorForeground") ?: error
    success = getSwingColor("Label.successForeground") ?: success
}

@Composable
fun intellijTextColors(): TextColors {
    val fallbackColors = if(isIntelliJThemeDark()) darkTextColors() else lightTextColors()

    return fallbackColors.apply { updateWithIntelliJColor() }
}

fun ToolBarColors.updateWithIntelliJColor() {
    buttonHover = getSwingColor("ActionButton.hoverBackground") ?: buttonHover
    buttonPressed = getSwingColor("ActionButton.pressedBackground") ?: buttonPressed
    iconSplitBorder = getSwingColor("ActionButton.hoverBackground") ?: iconSplitBorder
}

@Composable
fun intellijToolbarColors(): ToolBarColors {
    val fallbackColors = if(isIntelliJThemeDark()) darkToolBarColors() else lightToolBarColors()

    return fallbackColors.apply { updateWithIntelliJColor() }
}

fun ProgressColors.updateWithIntelliJColor() {
    progress = getSwingColor("ProgressBar.progressColor") ?: progress
    bg = getSwingColor("ProgressBar.background") ?: bg
}

@Composable
fun intellijProgressColor(): ProgressColors {
    val fallbackColors = if(isIntelliJThemeDark()) darkProgressColors() else lightProgressColors()

    return fallbackColors.apply { updateWithIntelliJColor() }
}

fun ScrollColors.updateWithIntelliJColor() {
    bg = getSwingColor("ScrollBar.background") ?: bg
}

@Composable
fun intellijScrollColors(): ScrollColors {
    val fallbackColors = if(isIntelliJThemeDark()) darkScrollColors() else lightScrollColors()

    return fallbackColors.apply { updateWithIntelliJColor() }
}

fun TabColors.updateWithIntelliJColor() {
    selection = getSwingColor("DefaultTabs.underlineColor", "TabbedPane.underlineColor") ?: selection
    focus = getSwingColor("TabbedPane.focusColor") ?: focus
    selectionInactive = getSwingColor("DefaultTabs.inactiveUnderlineColor") ?: selectionInactive
    hover = getSwingColor("DefaultTabs.hoverBackground")?.copy(alpha = .25f) ?: hover
    selectionDisabled = getSwingColor("TabbedPane.disabledUnderlineColor") ?: selectionDisabled
    bgSelected = getSwingColor("TabbedPane.highlight", "controlHighlight") ?: bgSelected
}

@Composable
fun intellijTabColors(): TabColors {
    val fallbackColors = if(isIntelliJThemeDark()) darkTabColors() else lightTabColors()

    return fallbackColors.apply { updateWithIntelliJColor() }
}

fun SelectionColors.updateWithIntelliJColor() {
    active = getSwingColor("Table.selectionBackground", "List.selectionBackground") ?: active
    inactive = getSwingColor("Table.hoverInactiveBackground") ?: inactive
    hover = getSwingColor("Table.hoverBackground", "List.hoverBackground", "Tree.hoverBackground") ?: hover
    lightActive = getSwingColor("Table.lightSelectionBackground", "Plugins.lightSelectionBackground") ?: lightActive
    lightInactive = getSwingColor("Table.lightSelectionInactiveBackground") ?: lightInactive
    completionPopup = getSwingColor("CompletionPopup.selectionBackground") ?: completionPopup
}

@Composable
fun intellijSelectionColors(): SelectionColors {
    val fallbackColors = if(isIntelliJThemeDark()) darkSelectionColors() else lightSelectionColors()

    return fallbackColors.apply { updateWithIntelliJColor() }
}

fun CheckBoxColors.updateWithIntelliJColor() {
    bg = getSwingColor("ComboBox.background") ?: bg
    bgSelected = getSwingColor("ComboBox.selectionBackground") ?: bgSelected
    bgDisabled = getSwingColor("ComboBox.disabledBackground") ?: bgDisabled
    border = getSwingColor("Borders.color") ?: border // dont know
    borderSelected = getSwingColor("ComboBox.selectionBackground") ?: borderSelected // dont know
    borderFocused = getSwingColor("ComboBox.selectionBackground") ?: borderFocused
    borderDisabled = getSwingColor("Component.disabledBorderColor") ?: borderDisabled
}

@Composable
fun intellijCheckboxColors(): CheckBoxColors {
    val fallbackColors = if(isIntelliJThemeDark()) darkCheckBoxColors() else lightCheckBoxColors()

    return fallbackColors.apply { updateWithIntelliJColor() }
}

fun IconColors.updateWithIntelliJColor() {
    selected = getSwingColor("Component.iconColor") ?: lightIconColors().selected
    disabled = getSwingColor("Button.disabledBorderColor") ?: lightIconColors().disabled
}

@Composable
fun intellijIconColors() = IconColors(
    selected = getSwingColor("Component.iconColor") ?: lightIconColors().selected,
    disabled = getSwingColor("Button.disabledBorderColor") ?: lightIconColors().disabled,
)

object SwingColor {
    var themeChangeState by mutableStateOf(Random.nextInt())
        private set

    fun onThemeChange() {
        themeChangeState = Random.nextInt()
    }
}

private val java.awt.Color.asComposeColor: Color get() = Color(red, green, blue, alpha)
