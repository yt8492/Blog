package ui.page

import api.Api
import com.yt8492.blog.common.Constants
import com.yt8492.blog.common.model.Entry
import emotion.react.css
import io.ktor.http.*
import kotlinx.browser.document
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import react.*
import react.dom.html.ReactHTML.div
import react.router.useLocation
import ui.component.entryRow
import web.cssom.Display
import web.cssom.FlexDirection

val entriesPage = FC<Props> {
    val location = useLocation()
    val page = parseQueryString(location.search)["page"]?.toIntOrNull() ?: 1
    val (state, setState) = useState(listOf<Entry>())
    useEffect(page) {
        document.title = Constants.BLOG_TITLE
        MainScope().launch {
            val entries = Api.getPublicEntries(page)
            setState(entries)
        }
    }
    div {
        state.reversed().forEach { entry ->
            entryRow {
                this.entry = entry
            }
        }

        css {
            display = Display.flex
            flexDirection = FlexDirection.column
        }
    }
}
