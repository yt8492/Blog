package ui.component

import kotlinx.css.*
import lib.reactmarkdown.remarkGfm
import lib.reactmarkdown.reactMarkdown
import lib.reactsyntaxhighlighter.prismRender
import react.Props
import react.fc
import styled.css
import styled.styledDiv
import kotlin.js.json

val markdown = fc<MarkdownProps> { props ->
    styledDiv {
        reactMarkdown {
            attrs.children = props.src
            attrs.plugins = listOf(remarkGfm)
        }

        css {
            padding = Padding(10.px, 30.px, 10.px)
            child("table") {
                margin = Margin(15.px, 0.px)
                padding = Padding(0.px)
                borderSpacing = 0.px
                borderCollapse = BorderCollapse.collapse
            }
            child("table tr") {
                borderTop = Border(1.px, BorderStyle.solid, Color("#cccccc"))
                backgroundColor = Color.white
                margin = Margin(0.px)
                padding = Padding(0.px)
            }
            child("table tr:nth-child(2n)") {
                backgroundColor = Color("#f8f8f8")
            }
            child("table tr th") {
                fontWeight = FontWeight.bold
                border = Border(1.px, BorderStyle.solid, Color("#cccccc"))
                textAlign = TextAlign.left
                margin = Margin(0.px)
                padding = Padding(6.px, 13.px)
            }
            child("table tr td") {
                border = Border(1.px, BorderStyle.solid, Color("#cccccc"))
                textAlign = TextAlign.left
                margin = Margin(0.px)
                padding = Padding(6.px, 13.px)
            }
            child("table tr th :first-child, table tr td :first-child") {
                marginTop = 0.px
            }
            child("table tr th :last-child, table tr td :last-child") {
                marginBottom = 0.px
            }
            child("code") {
                padding = Padding(0.2.em, 0.4.em)
                margin = Margin(0.px)
                fontSize = 85.pct
                backgroundColor = Color("rgba(27,31,35,.05)")
            }
            child("pre") {
                wordWrap = WordWrap.normal
            }
            child("pre>code") {
                padding = Padding(0.px)
                margin = Margin(0.px)
                fontSize = 100.pct
                wordBreak = WordBreak.normal
                whiteSpace = WhiteSpace.pre
                backgroundColor = Color.transparent
                borderWidth = 0.px

            }
        }
    }
}

external interface MarkdownProps : Props {
    var src: String
}
