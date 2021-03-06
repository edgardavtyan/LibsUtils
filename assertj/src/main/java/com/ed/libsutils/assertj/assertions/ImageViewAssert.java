package com.ed.libsutils.assertj.assertions;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.test.InstrumentationRegistry;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.ImageViewCompat;
import android.widget.ImageView;

import com.ed.libsutils.utils.BitmapResizer;
import com.ed.libsutils.utils.DpConverter;

import org.assertj.core.api.AbstractAssert;

public class ImageViewAssert extends AbstractAssert<ImageViewAssert, ImageView> {
	private final Context context;

	public ImageViewAssert(ImageView actual) {
		super(actual, ImageViewAssert.class);
		context = InstrumentationRegistry.getTargetContext();
	}

	@SuppressWarnings("UnusedReturnValue")
	public ImageViewAssert hasImageResource(@DrawableRes int drawableRes) {
		Drawable actualDrawable = actual.getDrawable();
		Bitmap actualBitmap = getBitmap(actualDrawable);

		Drawable expectedDrawable = actualDrawable instanceof VectorDrawableCompat
				? VectorDrawableCompat.create(context.getResources(), drawableRes, context.getTheme())
				: ContextCompat.getDrawable(context, drawableRes);

		DrawableCompat.setTintList(expectedDrawable, ImageViewCompat.getImageTintList(actual));
		DrawableCompat.setTintMode(expectedDrawable, ImageViewCompat.getImageTintMode(actual));
		expectedDrawable.setColorFilter(actual.getColorFilter());
		expectedDrawable.setAlpha(actual.getImageAlpha());
		Bitmap expectedBitmap = getBitmap(expectedDrawable);

		String drawableId = context.getResources().getResourceEntryName(drawableRes);
		String errorMessage = "\nExpecting to have resource with id='%s'\n";

		isNotNull();
		if (!expectedBitmap.sameAs(actualBitmap))
			failWithMessage(errorMessage, drawableId);

		return this;
	}

	@SuppressWarnings("UnusedReturnValue")
	public ImageViewAssert hasScaledImageBitmap(Bitmap expectedBitmap, int expectedSizeDp) {
		int actualSizeDp = DpConverter.pixelToDp(actual.getDrawable().getIntrinsicWidth());
		if (expectedSizeDp != actualSizeDp) {
			failWithMessage(
					"Expected bitmap to has size %ddp, but was %ddp",
					expectedSizeDp, actualSizeDp);
		}

		int expectedSizePx = DpConverter.dpToPixel(expectedSizeDp);
		return hasImageBitmap(BitmapResizer.resize(expectedBitmap, expectedSizePx));
	}

	@SuppressWarnings("UnusedReturnValue")
	public ImageViewAssert hasImageBitmap(Bitmap expectedBitmap) {
		Bitmap actualBitmap = getBitmap(actual.getDrawable());
		String errorMessage = "\nExpecting bitmap to be\n<%s>\nbut was:\n<%s>\n";

		isNotNull();
		if (!expectedBitmap.sameAs(actualBitmap))
			failWithMessage(errorMessage, expectedBitmap, actualBitmap);

		return this;
	}

	@SuppressWarnings("UnusedReturnValue")
	public ImageViewAssert hasImageAlpha(int expectedAlpha) {
		int actualAlpha = actual.getImageAlpha();
		String errorMessage = "\nExpected ImageAlpha to be <%s> but was <%s>\n";

		isNotNull();
		if (expectedAlpha != actualAlpha) {
			failWithMessage(errorMessage, expectedAlpha, actualAlpha);
		}

		return this;
	}

	private Bitmap getBitmap(Drawable drawable) {
		Bitmap result;
		if (drawable instanceof BitmapDrawable) {
			result = ((BitmapDrawable) drawable).getBitmap();
		} else {
			int width = drawable.getIntrinsicWidth();
			int height = drawable.getIntrinsicHeight();
			// Some drawables have no intrinsic width - e.g. solid colours.
			if (width <= 0) {
				width = 1;
			}
			if (height <= 0) {
				height = 1;
			}

			result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
			Canvas canvas = new Canvas(result);
			drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
			drawable.draw(canvas);
		}
		return result;
	}
}
