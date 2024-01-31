import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity

@Composable
fun TransparentOverlay(
	modifier: Modifier = Modifier,
	onClick: () -> Unit
) {

	Box(
		modifier = modifier
			.fillMaxSize()
			.clickable(
				onClick = onClick,
			)
			.background(
				color = Color.Black.copy(alpha = 0.5f),
				shape = MaterialTheme.shapes.small
			),
		contentAlignment = Alignment.Center
	) {
		// Contenido del panel transparente (opcional)
		// Puedes agregar más elementos dentro de este Box si quieres contenido en el panel.
	}
}