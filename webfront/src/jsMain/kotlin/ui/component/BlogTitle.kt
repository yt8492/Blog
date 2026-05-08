package ui.component

import com.yt8492.blog.common.Constants
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.header
import tanstack.react.router.Link
import ui.page.ENTRIES_PATH
import web.cssom.*

val blogTitle = FC<Props> {
    header {
        Link {
            + Constants.BLOG_TITLE
            to = ENTRIES_PATH
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
