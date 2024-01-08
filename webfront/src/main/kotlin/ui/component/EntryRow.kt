package ui.component

import com.yt8492.blog.common.model.Entry
import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import react.Props
import react.fc
import react.router.dom.Link
import styled.css
import styled.styled
import styled.styledDiv

val entryRow = fc<EntryRowProps> { props ->
    val entry = props.entry
    styledDiv {
        key = entry.id.value
        entryHeader {
            attrs.entry = entry
        }

        styledDiv {
            + entry.content

            css {
                overflow = Overflow.hidden
                textOverflow = TextOverflow.clip
                whiteSpace = WhiteSpace.nowrap
                marginTop = 12.px
            }
        }

        styledDiv {
            styled(Link)() {
                + "記事を読む"
                attrs.to = "/entries/${entry.id.value}"

                css {
                    fontSize = 16.px
                    fontWeight = FontWeight.bold
                    color = Color.white
                    textDecoration = TextDecoration.none
                    backgroundColor = Color("#16212C")
                    padding = Padding(8.px, 16.px)
                    boxSizing = BoxSizing.borderBox
                }
            }

            css {
                display = Display.flex
                flexDirection = FlexDirection.row
                justifyContent = JustifyContent.flexEnd
                marginTop = 12.px
            }
        }
        css {
            display = Display.flex
            flexDirection = FlexDirection.column
            width = 100.pct
            paddingBottom = 16.px
            borderBottom = Border(1.px, BorderStyle.solid, Color.lightGray)
        }
    }
}

external interface EntryRowProps : Props {
    var entry: Entry
}
