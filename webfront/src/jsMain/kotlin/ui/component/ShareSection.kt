package ui.component

import com.yt8492.blog.common.Constants
import com.yt8492.blog.common.model.Entry
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import web.cssom.Color
import web.cssom.Display
import web.cssom.FlexDirection
import web.cssom.px

val shareSection = FC<ShareSectionProps> { props ->
    val entry = props.entry
    val (text, url) = if (entry != null) {
        val title = """${entry.title} - ${Constants.BLOG_TITLE}"""
        val url = "https://blog.yt8492.com/entries/${entry.id.value}"
        encodeURIComponent(title) to encodeURIComponent(url)
    } else {
        val title = Constants.BLOG_TITLE
        val url = "https://blog.yt8492.com/"
        encodeURIComponent(title) to encodeURIComponent(url)
    }
    val twitterHref = "https://twitter.com/intent/tweet?text=$text&url=$url"
    val facebookHref = "https://www.facebook.com/sharer/sharer.php?u=$url"
    val hatenaBookmarkHref = "https://b.hatena.ne.jp/entry/panel/?mode=confirm&url=$url&title=$text"
    div {
        linkButton {
             this.url = twitterHref
             src = "./logo/twitter-brands.svg"
             color = Color("#1b95e0")
        }
        linkButton {
             this.url = facebookHref
             src = "./logo/facebook-square-brands.svg"
             color = Color("#3b5999")
        }
        linkButton {
             this.url = hatenaBookmarkHref
             src = "./logo/hatenabookmark-logomark.svg"
             color = Color("#4ba3d9")
        }

        css {
            display = Display.flex
            flexDirection = FlexDirection.row
            marginTop = 20.px
        }
    }
}

external interface ShareSectionProps : Props {
    var entry: Entry?
}

external fun encodeURIComponent(str: String): String
