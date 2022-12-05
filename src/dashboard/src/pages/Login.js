import React from "react";
import './Login.css'
import {authen} from '../services/AuthenProvider';

class FluidInput extends React.Component {
  
  constructor(props) {
    super(props);
    this.state = {
      focused: false,
      value: ""
    };
  }
  
  focusField() {
    const { focused } = this.state;
    this.setState({
      focused: !focused
    });
  }

  handleChange(event) {
    const { target } = event;
    const { value } = target;
    this.setState({
      value: value
    });
    this.props.onChange(value)
  }

  render() {
    const { type, label, style, id } = this.props;
    const { focused, value } = this.state;
    
    let inputClass = "fluid-input";
    if (focused) {
      inputClass += " fluid-input--focus";
    } else if (value != "") {
      inputClass += " fluid-input--open";
    }
    
    return (
      <div className={inputClass} style={style}>
        <div className="fluid-input-holder">
            <input 
              className="fluid-input-input"
              type={type}
              id={id}
              onFocus={this.focusField.bind(this)}
              onBlur={this.focusField.bind(this)}
              onChange={this.handleChange.bind(this)}
              autocomplete="off"
            />
            <label className="fluid-input-label" forHtml={id}>{label}</label>
        </div>
      </div>
    );
  }
}

class Button extends React.Component {
  render() {
    return (
      <div className={`button ${this.props.buttonClass}`} onClick={this.props.onClick}>
        {this.props.buttonText}
      </div>
    );
  }
}

class LoginPage extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      username: "",
      password: ""
    };
  }

  handleLogin() {
    const {username, password} = this.state;
    authen(username, password)
    .then(() => {

    })
    .catch(() => {
      
    })
  }

  onChangeUsername(username) {
    this.setState({username})
  }

  onChangePassword(password){
    this.setState({password})
  }

  render() {
    const style = {
      margin: "15px 0"
    };
    return (
      <div className="login-container">
        <div className="title">
         Login
        </div>
        <FluidInput type="text" label="name" id="name" style={style} onChange={this.onChangeUsername.bind(this)}/>
        <FluidInput type="password" label="password" id="password" style={style} onChange={this.onChangePassword.bind(this)} />
        <Button buttonText="log in" buttonClass="login-button" onClick={this.handleLogin.bind(this)}/>
      </div>
    );
  }
}

export default LoginPage;