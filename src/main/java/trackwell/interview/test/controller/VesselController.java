package trackwell.interview.test.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import trackwell.interview.test.errorhandling.ValidationError;
import trackwell.interview.test.errorhandling.ValidationErrorBuilder;
import trackwell.interview.test.helpers.UnitHelper;
import trackwell.interview.test.model.VesselInput;
import trackwell.interview.test.model.VesselOutput;

@RestController
@Validated
public class VesselController {

	@PostMapping("/inserVessel")
	@ResponseBody
	public VesselOutput insertVessel(@Valid @RequestBody VesselInput externalVessel) throws Exception {

		VesselOutput vessel = UnitHelper.createVesselOutput(externalVessel);

		return vessel;
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ValidationError handleException(MethodArgumentNotValidException exception) {
		return createValidationError(exception);
	}

	private ValidationError createValidationError(MethodArgumentNotValidException e) {
		return ValidationErrorBuilder.fromBindingErrors(e.getBindingResult());
	}
}
