package ui.component

import com.yt8492.blog.common.Constants
import com.yt8492.blog.common.model.Entry
import kotlinx.css.*
import react.Props
import react.fc
import styled.css
import styled.styledDiv

val shareSection = fc<ShareSectionProps> { props ->
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
    styledDiv {
        linkButton {
             attrs.url = twitterHref
             attrs.src = "./logo/twitter-brands.svg"
             attrs.color = Color("#1b95e0")
        }
        linkButton {
             attrs.url = facebookHref
             attrs.src = "./logo/facebook-square-brands.svg"
             attrs.color = Color("#3b5999")
        }
        linkButton {
             attrs.url = hatenaBookmarkHref
             attrs.src = "./logo/hatenabookmark-logomark.svg"
             attrs.color = Color("#4ba3d9")
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
