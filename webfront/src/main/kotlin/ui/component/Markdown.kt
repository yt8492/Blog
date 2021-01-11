package ui.component

import kotlinx.css.*
import kotlinx.css.properties.border
import kotlinx.css.properties.borderTop
import lib.reactmarkdown.gfm
import lib.reactmarkdown.reactMarkdown
import lib.reactsyntaxhaighlighter.prismRender
import react.RBuilder
import styled.css
import styled.styledDiv
import kotlin.js.json

fun RBuilder.markdown(src: String) {
    styledDiv {
        reactMarkdown(src, listOf(gfm), true, json("code" to prismRender))

        css {
            padding(10.px, 30.px, 10.px)
            child("table") {
                margin(15.px, 0.px)
                padding(0.px)
                borderSpacing = 0.px
                borderCollapse = BorderCollapse.collapse
            }
            child("table tr") {
                borderTop(1.px, BorderStyle.solid, Color("#cccccc"))
                backgroundColor = Color.white
                margin(0.px)
                padding(0.px)
            }
            child("table tr:nth-child(2n)") {
                backgroundColor = Color("#f8f8f8")
            }
            child("table tr th") {
                fontWeight = FontWeight.bold
                border(1.px, BorderStyle.solid, Color("#cccccc"))
                textAlign = TextAlign.left
                margin(0.px)
                padding(6.px, 13.px)
            }
            child("table tr td") {
                border(1.px, BorderStyle.solid, Color("#cccccc"))
                textAlign = TextAlign.left
                margin(0.px)
                padding(6.px, 13.px)
            }
            child("table tr th :first-child, table tr td :first-child") {
                marginTop = 0.px
            }
            child("table tr th :last-child, table tr td :last-child") {
                marginBottom = 0.px
            }
            child("code") {
                padding(0.2.em, 0.4.em)
                margin(0.px)
                fontSize = 85.pct
                backgroundColor = Color("rgba(27,31,35,.05)")
            }
            child("pre") {
                wordWrap = WordWrap.normal
            }
            child("pre>code") {
                padding(0.px)
                margin(0.px)
                fontSize = 100.pct
                wordBreak = WordBreak.normal
                whiteSpace = WhiteSpace.pre
                backgroundColor = Color.transparent
                borderWidth = 0.px

            }
        }
    }
}
