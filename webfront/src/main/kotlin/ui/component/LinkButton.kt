package ui.component

import kotlinx.css.*
import kotlinx.css.properties.border
import react.RBuilder
import styled.css
import styled.styledA
import styled.styledImg

fun RBuilder.linkButton(url: String, src: String, color: Color) {
    styledA(href = url, target = "_blank") {
        key = url
        attrs.rel = "nofollow"
        styledImg(src = src) {
            css {
                width = LinearDimension.inherit
            }
        }

        css {
            display = Display.flex
            justifyContent = JustifyContent.center
            this.color = color
            width = 24.px
            height = 24.px
            marginLeft = 20.px
            marginRight = 20.px
            padding(8.px)
            border(2.px, BorderStyle.solid, color, 25.pct)
        }
    }
}
