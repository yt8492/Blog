package ui.component

import com.yt8492.blog.common.model.Entry
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import tanstack.react.router.Link
import ui.page.entryPath
import web.cssom.*

val entryRow = FC<EntryRowProps> { props ->
    val entry = props.entry
    div {
        entryHeader {
            this.entry = entry
        }

        div {
            + entry.content

            css {
                overflow = Overflow.hidden
                textOverflow = TextOverflow.clip
                whiteSpace = WhiteSpace.nowrap
                marginTop = 12.px
            }
        }

        div {
            Link {
                + "記事を読む"
                to = entryPath(entry.id.value)

                css {
                    fontSize = 16.px
                    fontWeight = FontWeight.bold
                    color = NamedColor.white
                    textDecoration = None.none
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
            borderBottom = Border(1.px, LineStyle.solid, NamedColor.lightgray)
        }
    }
}

external interface EntryRowProps : Props {
    var entry: Entry
}
