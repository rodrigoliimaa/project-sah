package leonelcasado.mdi.utils{
  import flash.display.Graphics;
  import flash.geom.Point;
  import flash.geom.Rectangle;

  import mx.graphics.IStroke;

  public class GraphicsUtil
  {
    public static function _drawDashedLine(target:Graphics, stroke:IStroke, pattern:Array, drawingState:DashStruct, x0:Number, y0:Number, x1:Number, y1:Number):void
    {
      var dX:Number = x1 - x0;
      var dY:Number = y1 - y0;
      var len:Number = Math.sqrt(dX * dX + dY * dY);
      dX /= len;
      dY /= len;
      var tMax:Number = len;


      var t:Number = -drawingState.offset;
      var bDrawing:Boolean = drawingState.drawing;
      var patternIndex:int = drawingState.patternIndex;
      var styleInited:Boolean = drawingState.styleInited;
      while (t < tMax)
      {
        t += pattern[patternIndex];
        if (t < 0)
        {
          var x:int = 5;
        }
        if (t >= tMax)
        {
          drawingState.offset = pattern[patternIndex] - (t - tMax);
          drawingState.patternIndex = patternIndex;
          drawingState.drawing = bDrawing;
          drawingState.styleInited = true;
          t = tMax;
        }

        if (styleInited == false)
        {
          if (bDrawing){
          	//FLEX_TARGET_VERSION::flex4
            //{
            stroke.apply(target, new Rectangle(x0, y0, x1 - x0, y1 - y0), new Point(0, 0));
            //}
            //FLEX_TARGET_VERSION::flex3
            //{
            //  stroke.apply(target);
            //}

          }
          else
          {
            target.lineStyle(0, 0, 0);
          }
        }
        else
        {
          styleInited = false;
        }

        target.lineTo(x0 + t * dX, y0 + t * dY);

        bDrawing = !bDrawing;
        patternIndex = (patternIndex + 1) % pattern.length;
      }
    }


    public static function drawDashedLine(target:Graphics, stroke:IStroke, pattern:Array, x0:Number, y0:Number, x1:Number, y1:Number):void
    {
      target.moveTo(x0, y0);
      var struct:DashStruct = new DashStruct();
      _drawDashedLine(target, stroke, pattern, struct, x0, y0, x1, y1);
    }

    public static function drawDashedPolyLine(target:Graphics, stroke:IStroke, pattern:Array, points:Array):void
    {
      if (points.length == 0)
        return;

      var prev:Object = points[0];

      var struct:DashStruct = new DashStruct();
      target.moveTo(prev.x, prev.y);
      for (var i:int = 1; i < points.length; i++)
      {
        var current:Object = points[i];
        _drawDashedLine(target, stroke, pattern, struct, prev.x, prev.y, current.x, current.y);
        prev = current;
      }
    }
  }
}

class DashStruct
{
  public function init():void
  {
    drawing = true;
    patternIndex = 0;
    offset = 0;
  }
  public var drawing:Boolean = true;
  public var patternIndex:int = 0;
  public var offset:Number = 0;
  public var styleInited:Boolean = false;
}
