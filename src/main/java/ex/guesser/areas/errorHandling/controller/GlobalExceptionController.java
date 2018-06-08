package ex.guesser.areas.errorHandling.controller;//package ex.guesser.areas.errorHandling.controller;
//
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.servlet.ModelAndView;
//
//
//@ControllerAdvice
//public class GlobalExceptionController {
//
//    @ExceptionHandler(RuntimeException.class)
//    public ModelAndView getException(RuntimeException e) {
//        String errorMessage = "";
//
//        if (e.getMessage()==null){
//            if (e.getClass().isAnnotationPresent(ResponseStatus.class)) {
//                errorMessage = e.getClass().getAnnotation(ResponseStatus.class).reason();
//            } else {
//                errorMessage = "Error";
//            }
//
//        }
//        else {
//            errorMessage = e.getMessage();
//        }
//
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("error/error-template");
//        modelAndView.addObject("error", errorMessage);
//        return modelAndView;
//    }
//}
//
