/*
 * Mandelbrot Set Program
 * Author: J Femister
 * Date: August 2015
 */

import java.awt.Canvas
import java.awt.Color
import java.awt.Graphics
import java.awt.image.BufferedImage
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.math.MathContext
import java.text.NumberFormat
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JLabel


object Main {

  def main(args: Array[String]): Unit = {
    val imageHeight = 500
    val imageWidth = imageHeight
    val maxIterations = 40
    val mbsColor = Color.BLACK.getRGB
    val redlimit = maxIterations / 2 - 1
    val red = Color.RED.getRGB
    val white = Color.WHITE.getRGB

    val frame = new JFrame()
    val typex = BufferedImage.TYPE_INT_ARGB
    frame.setSize(imageWidth, imageHeight)
    val image = new BufferedImage(imageWidth, imageHeight, typex)
    val label = new JLabel(new ImageIcon(image))
    frame.add(label)
    frame.setVisible(true)

    var delta = 0.0
    var minRe = 0.0
    var maxRe = 0.0
    var minIm = 0.0

    val config = new File("config.ser")
    if (config.exists) {
      val ois = new ObjectInputStream(new FileInputStream(config))
      delta = ois.readObject.asInstanceOf[Double]
      minRe = ois.readObject.asInstanceOf[Double]
      maxRe = ois.readObject.asInstanceOf[Double]
      minIm = ois.readObject.asInstanceOf[Double]
      ois.close
    } else {
      delta = 0.5
      minRe = -2.0
      maxRe = 1.0 
      minIm = -1.2
    }

    var maxIm = minIm + (maxRe - minRe) * imageHeight / imageWidth

    var re_factor = (maxRe - minRe) / (imageWidth - 1)
    var im_factor = (maxIm - minIm) / (imageHeight - 1)

    var count: Int = 0
    var sum: Long = 0

    label.addMouseListener(new java.awt.event.MouseAdapter() {
      override def mouseClicked(evt: java.awt.event.MouseEvent) {
        val ctrlPressed = evt.isControlDown
        if (ctrlPressed) {
          val oos = new ObjectOutputStream(new FileOutputStream("config.ser"))
          oos.writeObject(delta)
          oos.writeObject(minRe)
          oos.writeObject(maxRe)
          oos.writeObject(minIm)
          oos.close
        } else {
          val altPressed = evt.isAltDown
          val x = evt.getX
          val y = imageWidth - evt.getY
          val px = x.toDouble / imageWidth
          val py = y.toDouble / imageHeight
          val tmpRe = (maxRe - minRe) * px + minRe
          val tmpIm = (maxIm - minIm) * py + minIm
          val factor = .50
          delta = if (altPressed) delta / factor else delta * factor
          minRe = tmpRe - delta
          maxRe = tmpRe + delta
          minIm = tmpIm - delta
          maxIm = tmpIm + delta
          re_factor = (maxRe - minRe) / (imageWidth - 1)
          im_factor = (maxIm - minIm) / (imageHeight - 1)
          genimage
        }
      }
    })

    def genPoints() = {
      val points =
        for (y <- 0 until imageHeight) yield {
          val c_im = maxIm - y * im_factor
          for (x <- 0 until imageWidth) yield {
            val c_re = minRe + x * re_factor
            (x, y, c_im, c_re)
          }
        }
      points.flatten.par
    }

    def genPoint(x: Int, y: Int, c_im: BigDecimal, c_re: BigDecimal) {
      var niter: Int = 0
      var z_re = c_re
      var z_im = c_im

      var isInside = true;
      for (
        n <- 0 until maxIterations if isInside
      ) {
        val z_re2 = z_re * z_re
        val z_im2 = z_im * z_im
        if (z_re2 + z_im2 > 4) {
          isInside = false
          niter = n
        } else {
          z_im = 2 * z_re * z_im + c_im
          z_re = z_re2 - z_im2 + c_re
        }
      }
      if (isInside) {
        image.setRGB(x, y, mbsColor)
      } else {
        if (niter >= 0 && niter <= redlimit)
          image.setRGB(x, y, red)
        else
          image.setRGB(x, y, white)
      }
    }

    def genimage() {
      val t0 = System.nanoTime()
      val points = genPoints
      points.foreach(point => genPoint(point._1, point._2, point._3, point._4))
      label.repaint()
      val t1 = System.nanoTime()
      val tmp = t1 - t0
      sum += tmp
      count += 1
      val avg = sum / count
      println("Elapsed time: " + NumberFormat.getInstance.format(tmp) + 
              " ns  Average: " + NumberFormat.getInstance.format(avg))
    }

    genimage
  }
}
