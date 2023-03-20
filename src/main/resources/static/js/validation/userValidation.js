
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

isPhone = function(seletor, message) {
	return {
		seletor: seletor,
		test: function(value) {
			var regex = /(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\b/
			return regex.test(value) != '' ? undefined : message
		}
	}
}

isEmail = function(seletor, message) {
	return {
		seletor: seletor,
		test: function(value) {
			var regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/
			return regex.test(value) == true ? undefined : message
		}
	}
}

isPassword = function(seletor, minLength) {
	return {
		seletor: seletor,
		test: function(value) {
			return value.length >= minLength ? undefined : `Mat khau phai toi thieu ${minLength} ki tu`
		}
	}
}

isPasswordConfirmation = function(seletor, getValue) {
	return {
		seletor: seletor,
		test: function(value) {
			return value === getValue() ? undefined : 'Nhap lai mat khau khong chinh xac'
		}
	}
}


Validator({
	form: '#form-add-user',
	formError: '.form-message',
	formGroup: '.form-group',
	rules: [
		isRequired('#fullname', 'Vui long nhap truong nay'),

		isRequired('#phone', 'Vui long nhap truong nay'),
		isPhone('#phone', 'So dien thoai kong hop le'),

		isRequired('#username', 'Vui long nhap truong nay'),

		isRequired('#password', 'Vui long nhap truong nay'),
		isPassword('#password', 3),

		isRequired('#password_confirmation', 'Vui long nhap truong nay'),
		isPasswordConfirmation('#password_confirmation', getValue = function() {
			const formElement = document.querySelector('#form-add-user')
			const password = formElement.querySelector('#password')
			return password.value
		}),
	]
})