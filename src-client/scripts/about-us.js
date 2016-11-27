const React = require('react')
const ReactDOM = require('react-dom')

const AboutUsView = React.createClass({
  render: function(){
    return (
      <div className="about-us-page-container">
        <div className="auth-home-icon-container">
          <a href=" "><i className="fa fa-home fa-4x auth-home-icon" aria-hidden="true"></i></a>
        </div>
        <div className="about-header text-center">
          <h2>LOGO</h2>
          <h1>Presta Trading Post</h1>
        </div>

        <div className="about-us-container">
          <h1><u>About Us</u></h1>
          <p><strong><em>Presta Trading Post</em></strong> is the ultimate meet-up spot for those who have and those who don't. Have you ever wanted to try out a stand-up paddle board but didn't want to have to fork out the big money just to try it out for a weekend?  How about, have you ever needed a special tool to fix something in an afternoon but didn't want to have to buy so that it can sit in your garage 99% of the time?  Or maybe you want to have a jam session tonight but you don't have an amplifier.  Well here at <strong><em>Presta Trading Post</em></strong> we have people who have these kinds of things and more that are just sitting in their garages for 99% of the year and for a nominal price, which in most cases can be totally negotiable, you can use these items and not have to buy them.  All you have to do is register with <strong><em>Presta Trading Post</em></strong> today, and then you can begin to see the benefits.  Or maybe you even have items sitting around in your garage that someone else might need to use.  Everybody wins.  So, register today.</p>
        </div>
      </div>
    )
  }
});

module.exports = AboutUsView
