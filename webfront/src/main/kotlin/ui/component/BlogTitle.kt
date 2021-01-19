package ui.component

import com.yt8492.blog.common.Constants
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
            + Constants.BLOG_TITLE
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
