const webpack = require("webpack")

var isProduction = process.env.IS_PRODUCTION

if (isProduction) {
    blogBaseUrl = "'https://blog.yt8492.com'"
}
else {
    blogBaseUrl = "'http://localhost:8080'"
}

const definePlugin = new webpack.DefinePlugin(
    {
        BLOG_BASE_URL: blogBaseUrl
    }
)

config.plugins.push(definePlugin)
