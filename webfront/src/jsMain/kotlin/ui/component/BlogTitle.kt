package ui.component

import com.yt8492.blog.common.Constants
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.header
import react.router.dom.Link
import web.cssom.*

val blogTitle = FC<Props> {
    header {
        Link {
            + Constants.BLOG_TITLE
            to = "/"
            css {
                color = NamedColor.black
                textDecoration = None.none
            }
        }
        css {
            display = Display.flex
            justifyContent = JustifyContent.center
            fontSize = 2.rem
            fontWeight = FontWeight.bold
            padding = Padding(28.px, 28.px)
        }
    }
}
