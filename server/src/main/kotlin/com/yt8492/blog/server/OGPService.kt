package com.yt8492.blog.server

import com.yt8492.blog.common.Constants
import com.yt8492.blog.common.model.Entry
import java.awt.Color
import java.awt.Font
import java.awt.RenderingHints
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO

object OGPService {

    private const val OGP_WIDTH = 1200
    private const val OGP_HEIGHT = 630

    fun createImageByteArray(entry: Entry): ByteArray {
        val image = BufferedImage(OGP_WIDTH, OGP_HEIGHT, BufferedImage.TYPE_INT_ARGB)
        val graphics2D = image.createGraphics().apply {
            setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY)
            setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
            setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY)
            setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE)
            setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON)
            setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR)
            setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
            setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE)
        }
        graphics2D.color = Color(0x16, 0x21, 0x2C)
        graphics2D.fillRect(0, 0, OGP_WIDTH, OGP_HEIGHT)
        graphics2D.color = Color.WHITE
        val font = Font(Font.SANS_SERIF, Font.PLAIN, 40)
        graphics2D.font = font
        val fontMetrics = graphics2D.fontMetrics
        val fontHeight = fontMetrics.height
        val entryTitleWidth = fontMetrics.stringWidth(entry.title)
        graphics2D.drawString(
            entry.title,
            OGP_WIDTH / 2 - entryTitleWidth / 2,
            OGP_HEIGHT / 2 - fontHeight
        )
        val blogTitleWidth = fontMetrics.stringWidth(Constants.BLOG_TITLE)
        graphics2D.drawString(
            Constants.BLOG_TITLE,
            OGP_WIDTH / 2 - blogTitleWidth / 2,
            OGP_HEIGHT / 2 + fontHeight
        )
        graphics2D.dispose()
        val outputStream = ByteArrayOutputStream()
        ImageIO.write(image, "png", outputStream)
        return outputStream.toByteArray()
    }
}
