package ui.component

import kotlinx.css.*
import react.RBuilder
import styled.css
import styled.styledHeader

fun RBuilder.blogTitle() {
    styledHeader {
        + """Log.d("yt8492", blog)"""

        css {
            display = Display.flex
            justifyContent = JustifyContent.center
            fontSize = 2.rem
            fontWeight = FontWeight.bold
            padding(28.px)
        }
    }
}
