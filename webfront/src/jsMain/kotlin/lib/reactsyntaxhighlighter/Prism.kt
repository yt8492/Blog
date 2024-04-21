@file:JsModule("react-syntax-highlighter")
@file:JsNonModule
package lib.reactsyntaxhighlighter

import react.FC
import react.Props
import react.PropsWithChildren

external object Prism : FC<PrismProps> {
    override var displayName: String? = definedExternally
}

external interface PrismProps : PropsWithChildren {
    var language: String
    var PreTag: String
    var style: dynamic
}
