package ui.component

import com.yt8492.blog.common.model.Entry
import emotion.react.css
import korlibs.time.DateFormat
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import web.cssom.*

val entryHeader = FC<EntryHeaderProps> { props ->
    val entry = props.entry
    h1 {
        + entry.title

        css {
            marginBottom = 8.px
        }
    }
    div {
        + entry.createdAt.toString(DateFormat("yyyy/MM/dd"))

        css {
            fontSize = 16.px
            color = NamedColor.gray
        }
    }
    div {
        entry.tags.forEach {
            div {
                + "# $it"
                key = it

                css {
                    fontSize = 12.px
                    border = Border(1.px, LineStyle.solid, NamedColor.gray)
                    borderRadius = 2.px
                    padding = Padding(2.px, 4.px)
                    marginRight = 4.px
                    boxSizing = BoxSizing.borderBox
                }
            }
        }

        css {
            display = Display.flex
            flexDirection = FlexDirection.row
            marginTop = 12.px
        }
    }
}

external interface EntryHeaderProps : Props {
    var entry: Entry
}
