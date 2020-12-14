package ui.component

import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import react.RBuilder
import styled.css
import styled.styledA
import styled.styledHeader

fun RBuilder.blogTitle() {
    styledHeader {
        styledA(href = "#/") {
            + """Log.d("yt8492", blog)"""

            css {
                color = Color.black
                textDecoration = TextDecoration.none
            }
        }

        css {
            display = Display.flex
            justifyContent = JustifyContent.center
            fontSize = 2.rem
            fontWeight = FontWeight.bold
            padding(28.px)
        }
    }
}
