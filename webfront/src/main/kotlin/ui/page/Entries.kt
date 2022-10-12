package ui.page

import api.Api
import com.yt8492.blog.common.Constants
import com.yt8492.blog.common.model.Entry
import io.ktor.http.*
import kotlinx.browser.document
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.css.Display
import kotlinx.css.FlexDirection
import kotlinx.css.display
import kotlinx.css.flexDirection
import react.*
import react.router.useLocation
import styled.css
import styled.styledDiv
import ui.component.entryRow

val entriesPage = fc<Props> {
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
    styledDiv {
        state.forEach { entry ->
            entryRow {
                attrs.entry = entry
            }
        }

        css {
            display = Display.flex
            flexDirection = FlexDirection.column
        }
    }
}
