package ui.component

import com.soywiz.klock.DateFormat
import com.yt8492.blog.common.model.Entry
import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import kotlinx.css.properties.border
import kotlinx.css.properties.borderBottom
import react.RBuilder
import styled.css
import styled.styledA
import styled.styledDiv
import styled.styledH1

fun RBuilder.entryRow(entry: Entry) {
    styledDiv {
        key = entry.id.value
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
            styledA("#/entries/${entry.id.value}") {
                + "記事を読む"

                css {
                    fontSize = 16.px
                    fontWeight = FontWeight.bold
                    color = Color.white
                    textDecoration = TextDecoration.none
                    backgroundColor = Color("#16212C")
                    padding(8.px, 16.px)
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
            borderBottom(1.px, BorderStyle.solid, Color.lightGray)
        }
    }
}
