package ui.component

import emotion.react.css
import js.reflect.unsafeCast
import react.FC
import react.Key
import react.Props
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.img
import web.cssom.*
import web.window.WindowTarget

val linkButton = FC<LinkButtonProps> { props ->
    a {
        href = props.url
        target = "_blank".unsafeCast<WindowTarget>()
        key = props.url.unsafeCast<Key>()
        rel = "nofollow"
        img {
            src = props.src
            css {
                width = Globals.inherit
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
            padding = Padding(8.px, 8.px)
            border = Border(2.px, LineStyle.solid, props.color)
            borderRadius = 25.pct
        }
    }
}

external interface LinkButtonProps : Props {
    var url: String
    var src: String
    var color: Color
}
