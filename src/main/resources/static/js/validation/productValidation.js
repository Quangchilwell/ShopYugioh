
function Validator(options) {
	var selectorRules = {}

	// Lay the cha cua 1 the con
	function getParentElement(inputElement, seletor) {
		while (inputElement.parentElement) {
			if (inputElement.parentElement.matches(seletor)) {
				return inputElement.parentElement
			}
			inputElement = inputElement.parentElement
		}
	}

	// Ham xac thuc
	function Validation(inputElement, rule) {
		var inputElement = formElement.querySelector(rule.seletor)
		var errorElement = getParentElement(inputElement, options.formGroup).querySelector(options.formError)
		var errorMessage = ''

		var rules = selectorRules[rule.seletor]
		for (var i = 0; i < rules.length; i++) {
			errorMessage = rules[i](inputElement.value)
			if (errorMessage) break
		}

		if (errorMessage != undefined) {
			errorElement.innerText = errorMessage
			getParentElement(inputElement, options.formGroup).classList.add('invalid')
		}
		else {
			errorElement.innerText = ''
			getParentElement(inputElement, options.formGroup).classList.remove('invalid')
		}

		if (errorMessage) return true
		return false
	}

	const formElement = document.querySelector(options.form)
	if (formElement) {
		options.rules.forEach(function(rule) {

			// Dua cac rule vao cac input
			if (Array.isArray(selectorRules[rule.seletor])) {
				selectorRules[rule.seletor].push(rule.test)
			}
			else {
				selectorRules[rule.seletor] = [rule.test]
			}

			var inputElement = formElement.querySelector(rule.seletor)
			var errorElement = getParentElement(inputElement, options.formGroup).querySelector(options.formError)
			if (inputElement) {
				inputElement.onblur = function() {
					Validation(inputElement, rule)
				}

				inputElement.oninput = function() {
					errorElement.innerText = ''
					getParentElement(inputElement, options.formGroup).classList.remove('invalid')
				}
			}

		});

		// Xu li nut submit
		formElement.onsubmit = function(e) {
			var formValid = true
			options.rules.forEach(function(rule) {
				var inputElement = formElement.querySelector(rule.seletor)
				var isInValid = Validation(inputElement, rule)
				if (isInValid == true) formValid = false
			});

			if (formValid == false) {
				e.preventDefault()
			}
		}
	};
}

isRequired = function(seletor, message) {
	return {
		seletor: seletor,
		test: function(value) {
			return value != '' ? undefined : message
		}
	}
}




Validator({
	form: '#form-product',
	formError: '.form-message',
	formGroup: '.form-group',
	rules: [
		isRequired('#name', 'Vui long nhap truong nay'),

		isRequired('#price', 'Vui long nhap truong nay'),
		isNumber('#price', 'Du lieu nhap khong hop le'),

		isRequired('#quantity', 'Vui long nhap truong nay'),
		

	]
})