package ui.page

import api.Api
import com.yt8492.blog.common.model.Entry
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import react.*
import react.dom.div

val entriesPage = functionalComponent<EntriesProps> { props ->
    val (state, setState) = useState(listOf<Entry>())
    useEffect(listOf(props.page)) {
        GlobalScope.launch {
            MainScope().launch {
                val entries = Api.getPublicEntries(props.page)
                setState(entries)
            }
        }
    }
    state.forEach { entry ->
        div {
            + entry.title
        }
    }
}

interface EntriesProps : RProps {
    var page: Int
}
