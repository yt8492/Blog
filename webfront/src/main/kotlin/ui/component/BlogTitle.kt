package ui.component

import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import react.RBuilder
import react.rClass
import react.router.dom.LinkComponent
import styled.css
import styled.styled
import styled.styledHeader

fun RBuilder.blogTitle() {
    styledHeader {
        styled(LinkComponent::class.rClass)() {
            + """Log.d("yt8492", blog)"""
            attrs.to = "/"
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
