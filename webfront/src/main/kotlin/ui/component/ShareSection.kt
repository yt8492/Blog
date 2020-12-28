package ui.component

import com.yt8492.blog.common.model.Entry
import kotlinx.css.*
import org.intellij.markdown.html.urlEncode
import react.RBuilder
import styled.css
import styled.styledDiv

fun RBuilder.shareSection(entry: Entry? = null) {
    val (text, url) = if (entry != null) {
        val title = """${entry.title} - Log.d("yt8492", blog)"""
        val url = "https://blog.yt8492.com/entries/${entry.id.value}"
        urlEncode(title) to urlEncode(url)
    } else {
        val title = """Log.d("yt8492", blog)"""
        val url = "https://blog.yt8492.com/"
        urlEncode(title) to urlEncode(url)
    }
    val twitterHref = "https://twitter.com/intent/tweet?text=$text&url=$url"
    val facebookHref = "https://www.facebook.com/sharer/sharer.php?u=$url"
    val hatenaBookmarkHref = "https://b.hatena.ne.jp/entry/panel/?mode=confirm&url=$url&title=$text"
    styledDiv {
        linkButton(twitterHref, "./logo/twitter-brands.svg", Color("#1b95e0"))
        linkButton(facebookHref, "./logo/facebook-square-brands.svg", Color("#3b5999"))
        linkButton(hatenaBookmarkHref, "./logo/hatenabookmark-logomark.svg", Color("#4ba3d9"))

        css {
            display = Display.flex
            flexDirection = FlexDirection.row
            marginTop = 20.px
        }
    }
}
