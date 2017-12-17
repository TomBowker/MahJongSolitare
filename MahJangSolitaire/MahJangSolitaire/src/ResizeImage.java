import java.awt.Image;

//This helps resize the images

public class ResizeImage {
	public Image getImage (Image image, int width, int height) {
		
		//Initialized in case we have something return unexpectedly
		int originalHeight = image.getHeight(null);
		int originalWidth = image.getWidth(null);
		
		//scale the dimensions
		if(originalHeight > height || originalWidth > width) {
			
			int changedHeight = 0;
			int changedWidth = 0;
			
			if(originalWidth > originalHeight) {
				changedWidth = width;
				float dimensionRatio = (float)originalWidth / (float)width;
				changedHeight = Math.round(originalHeight / dimensionRatio);
			}
			else if(originalHeight > originalWidth) {
				changedHeight = height;
				float dimensionRatio = (float)originalHeight / (float)height;
				changedWidth = Math.round(originalWidth / dimensionRatio);
			}
			else if(originalWidth == originalHeight) {
				changedHeight = height;
				changedWidth = width;
			}
			
			//return the new image
			return image.getScaledInstance(changedWidth, changedHeight, Image.SCALE_SMOOTH);
		}
		//return the original image
		return image;
	}

}
