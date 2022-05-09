package ui.component

import com.yt8492.blog.common.Constants
import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import react.Props
import react.fc
import react.router.dom.Link
import styled.css
import styled.styled
import styled.styledHeader

val blogTitle = fc<Props> {
    styledHeader {
        styled(Link)() {
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
