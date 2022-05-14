package ui.component

import com.soywiz.klock.DateFormat
import com.yt8492.blog.common.model.Entry
import kotlinx.css.*
import kotlinx.css.properties.border
import react.Props
import react.fc
import styled.css
import styled.styledDiv
import styled.styledH1

val entryHeader = fc<EntryHeaderProps> { props ->
    val entry = props.entry
    styledH1 {
        + entry.title

        css {
            marginBottom = 8.px
        }
    }
    styledDiv {
        + entry.createdAt.toString(DateFormat("yyyy/MM/dd"))

        css {
            fontSize = 16.px
            color = Color.gray
        }
    }
    styledDiv {
        entry.tags.forEach {
            styledDiv {
                + "# $it"
                key = it

                css {
                    fontSize = 12.px
                    border(1.px, BorderStyle.solid, Color.gray, 2.px)
                    padding(2.px, 4.px)
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
