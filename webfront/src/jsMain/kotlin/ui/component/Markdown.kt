package ui.component

import emotion.react.css
import lib.reactmarkdown.remarkGfm
import lib.reactmarkdown.reactMarkdown
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import web.cssom.*

val markdown = FC<MarkdownProps> { props ->
    div {
        reactMarkdown {
            + props.src
            plugins = listOf(remarkGfm)
        }

        css {
            padding = Padding(10.px, 30.px, 10.px)
            Selector("table")() {
                margin = Margin(15.px, 0.px)
                padding = Padding(0.px, 0.px)
                borderSpacing = 0.px
                borderCollapse = BorderCollapse.collapse
            }
            Selector("table tr")() {
                borderTop = Border(1.px, LineStyle.solid, Color("#cccccc"))
                backgroundColor = NamedColor.white
                margin = Margin(0.px, 0.px)
                padding = Padding(0.px, 0.px)
            }
            Selector("table tr:nth-Selector(2n)")() {
                backgroundColor = Color("#f8f8f8")
            }
            Selector("table tr th")() {
                fontWeight = FontWeight.bold
                border = Border(1.px, LineStyle.solid, Color("#cccccc"))
                textAlign = TextAlign.left
                margin = Margin(0.px, 0.px)
                padding = Padding(6.px, 13.px)
            }
            Selector("table tr td")() {
                border = Border(1.px, LineStyle.solid, Color("#cccccc"))
                textAlign = TextAlign.left
                margin = Margin(0.px, 0.px)
                padding = Padding(6.px, 13.px)
            }
            Selector("table tr th :first-child, table tr td :first-child")() {
                marginTop = 0.px
            }
            Selector("table tr th :last-child, table tr td :last-child")() {
                marginBottom = 0.px
            }
            Selector("code")() {
                padding = Padding(0.2.em, 0.4.em)
                margin = Margin(0.px, 0.px)
                fontSize = 85.pct
                backgroundColor = Color("rgba(27,31,35,.05)")
            }
            Selector("pre")() {
                wordWrap = WordWrap.normal
            }
            Selector("pre>code")() {
                padding = Padding(0.px, 0.px)
                margin = Margin(0.px, 0.px)
                fontSize = 100.pct
                wordBreak = WordBreak.normal
                whiteSpace = WhiteSpace.pre
                backgroundColor = NamedColor.transparent
                borderWidth = 0.px

            }
        }
    }
}

external interface MarkdownProps : Props {
    var src: String
}
