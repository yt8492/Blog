package ui.component

import kotlinx.css.*
import kotlinx.css.properties.border
import react.Props
import react.fc
import styled.css
import styled.styledA
import styled.styledImg

val linkButton = fc<LinkButtonProps> { props ->
    styledA(href = props.url, target = "_blank") {
        key = props.url
        attrs.rel = "nofollow"
        styledImg(src = props.src) {
            css {
                width = LinearDimension.inherit
            }
        }

        css {
            display = Display.flex
            justifyContent = JustifyContent.center
            color = props.color
            width = 24.px
            height = 24.px
            marginLeft = 20.px
            marginRight = 20.px
            padding(8.px)
            border(2.px, BorderStyle.solid, color, 25.pct)
        }
    }
}

external interface LinkButtonProps : Props {
    var url: String
    var src: String
    var color: Color
}
